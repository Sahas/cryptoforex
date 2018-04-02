package com.crypto.forex.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.crypto.forex.mongo.documents.Coin;
import com.crypto.forex.mongo.documents.CoinPrice;
import com.crypto.forex.mongo.documents.FullExchangeCoinPriceData;
import com.crypto.forex.repositories.FullExchangeCoinPriceDataRepository;
import com.crypto.forex.utils.ConversionUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class CoinDataHandler {

  private static Logger logger = LoggerFactory.getLogger(CoinDataHandler.class);

  @Autowired
  private FullExchangeCoinPriceDataRepository exchangeCoinPriceRepository;

  @Autowired
  private ConversionUtils conversionUtils;

  @Autowired
  List<String> availableExchanges;

  public Double fetchLowestPricedExchangeForCoin(final List<CoinPrice> coinPrices,
      final Coin currencyCoin) {
    Double lowest = Double.MAX_VALUE;
    Double currPrice = 0.0;
    for (final CoinPrice coinprice : coinPrices) {
      currPrice = conversionUtils.getExchangePriceFor(coinprice.getPeggedcoin().getSym(),
          currencyCoin.getSym()) * coinprice.getPrice();
      if (currPrice < lowest) {
        lowest = currPrice;
      }
    }
    return lowest;
  };

  public static Double findProfitOrLossPercentage(final Double basePrice, final Double sellPrice) {
    final DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.CEILING);
    final BigDecimal roundedOffPercentage =
        new BigDecimal((sellPrice - basePrice) * 100 / basePrice).setScale(2,
            BigDecimal.ROUND_HALF_UP);
    return roundedOffPercentage.doubleValue();
  }

  public Map<String, List<CoinPrice>> getLatestCoinPricesInExchanges(
      final List<String> exchangeIds) {
    final List<CoinPrice> currencyPrices = new ArrayList<>();
    final Map<String, List<CoinPrice>> allCoinCurrencyPriceListMap = new HashMap<>();
    logger.debug("Querying repository for latest Exchange Coin Data");
    final Page<FullExchangeCoinPriceData> fullExchangeCoinPriceData = exchangeCoinPriceRepository
        .findAll(PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "createdtime")));
    fullExchangeCoinPriceData.getContent().get(0).getExchangescoinprices()
        .forEach(exchangeCoinPrices -> {
          if (exchangeIds.contains(exchangeCoinPrices.getExchange().getId())) {
            currencyPrices.addAll(exchangeCoinPrices.getCoinprices()); 
          }
        });


    logger.debug("Creating CoinPrices for each coin based on latest exchange coin data");
    for (final CoinPrice coinCurrencyPrice : currencyPrices) {
      final String currCoinSym = coinCurrencyPrice.getBasecoin().getSym();
      allCoinCurrencyPriceListMap.putIfAbsent(currCoinSym, new ArrayList<CoinPrice>());
      allCoinCurrencyPriceListMap.get(currCoinSym).add(coinCurrencyPrice);
    }
    return allCoinCurrencyPriceListMap;
  }

  public Map<String, List<CoinPrice>> getLatestCoinPricesOfAllExchanges() {
    return getLatestCoinPricesInExchanges(availableExchanges);
  }

  public Map<String, Set<JsonNode>> getCoinPricesInAllExchangesWithNormalizedPrices(
      final Map<String, List<CoinPrice>> latestCoinPricesInAllExchanges,
      final Coin normalizedCoin) {
    final Map<String, Set<JsonNode>> coinPricesInAllExchangesWithNormalizedPrices =
        new HashMap<>();
    final ObjectMapper mapper = new ObjectMapper();
    latestCoinPricesInAllExchanges.forEach((coinSym, coinPrices) -> {
      final Double lowestPrice =
          this.fetchLowestPricedExchangeForCoin(coinPrices, normalizedCoin);
      coinPricesInAllExchangesWithNormalizedPrices.putIfAbsent(coinSym,
          new TreeSet<>(
              Comparator.comparingDouble(node -> node.get("gainPercent").asDouble())));
      coinPrices.forEach(coinPrice -> {
        final ObjectNode normalizedCoinPrice = mapper.valueToTree(coinPrice);
        final Double normalizedPrice = coinPrice.getPrice()
            * conversionUtils.getExchangePriceFor(coinPrice.getPeggedcoin().getSym(),
                normalizedCoin.getSym());
        normalizedCoinPrice.set("normalizedCoin", mapper.valueToTree(normalizedCoin));
        normalizedCoinPrice.put("normalizedCoinPrice", normalizedPrice);
        normalizedCoinPrice.put("gainPercent",
            findProfitOrLossPercentage(lowestPrice, normalizedPrice));
        coinPricesInAllExchangesWithNormalizedPrices.get(coinSym).add(normalizedCoinPrice);
      });
    });
    return coinPricesInAllExchangesWithNormalizedPrices;
  }

  public Map<String, Set<JsonNode>> getBestTransferPricesInExchanges(String from, String to) {
    getLatestCoinPricesInExchanges(Arrays.asList(from, to));
  }
}
