package com.crypto.forex.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.crypto.forex.MasterBean;
import com.crypto.forex.mongo.documents.Coin;
import com.crypto.forex.mongo.documents.CoinPrice;
import com.crypto.forex.utils.Currencies;

@Component
public class ParseExchangesTask {
  private static final Logger logger = LoggerFactory.getLogger(ParseExchangesTask.class);

  @Autowired
  private MasterBean masterBean;

  @Scheduled(cron = "0 0/5 * * * ?")
  public void fetchAndPutAllExchangeDataInDB() {
    final List<ExchangeParseDef> allParseDefs = masterBean.getAllparseDefsForAvailableExchanges();
    final Map<Coin, List<CoinPrice>> allCoinCurrencyPriceListMap = new HashMap<>();
    for (final ExchangeParseDef parseDef : allParseDefs) {
      final List<CoinPrice> coinPrices = ScraperDeterminer.getScraperFor(parseDef).scrape(parseDef);
      final List<CoinPrice> currencyPrices = getCurrencyPricesOfCoins(coinPrices);
      // store in DB
      for (final CoinPrice coinCurrencyPrice : currencyPrices) {
        final Coin currCoin = coinCurrencyPrice.getBasecoin();
        if (allCoinCurrencyPriceListMap.containsKey(currCoin)) {
          allCoinCurrencyPriceListMap.get(currCoin).add(coinCurrencyPrice);
        } else {
          final List<CoinPrice> currencyCoinPrices = new ArrayList<CoinPrice>();
          currencyCoinPrices.add(coinCurrencyPrice);
          allCoinCurrencyPriceListMap.put(currCoin, currencyCoinPrices);
        }
      }
    }

    // store in db
  }

  public List<CoinPrice> getCurrencyPricesOfCoins(final List<CoinPrice> rawCoinPrices) {
    final Map<Coin, List<CoinPrice>> coinCurrencyPriceMap = new HashMap<>();
    final Set<CoinPrice> currencyPricesOfCoins = new HashSet<>();

    for (final Iterator<CoinPrice> iter = rawCoinPrices.iterator(); iter.hasNext();) {
      final CoinPrice rawCoinPrice = iter.next();
      if (Currencies.isCurrency(rawCoinPrice.getPeggedcoin().getSym())) {
        if (coinCurrencyPriceMap.containsKey(rawCoinPrice.getBasecoin())) {
          coinCurrencyPriceMap.get(rawCoinPrice.getBasecoin()).add(rawCoinPrice);
        } else {
          final List<CoinPrice> currencyCoinPrices = new ArrayList<CoinPrice>();
          currencyCoinPrices.add(rawCoinPrice);
          coinCurrencyPriceMap.put(rawCoinPrice.getBasecoin(), currencyCoinPrices);
        }
        currencyPricesOfCoins.add(rawCoinPrice);
        iter.remove();
      }
    }

    for (final CoinPrice rawCoinPrice : rawCoinPrices) {
      final Coin peggedCoin = rawCoinPrice.getPeggedcoin();
      if (coinCurrencyPriceMap.containsKey(peggedCoin)) {
        for (final CoinPrice peggedCoinCurrencyPrice : coinCurrencyPriceMap.get(peggedCoin)) {
          final Double currencyPriceValue =
              rawCoinPrice.getPrice() * peggedCoinCurrencyPrice.getPrice();
          final CoinPrice currCoinCurrencyPrice =
              new CoinPrice(rawCoinPrice.getExchange().getId(), rawCoinPrice.getBasecoin().getSym(),
                  peggedCoinCurrencyPrice.getPeggedcoin().getSym(), currencyPriceValue);
          currencyPricesOfCoins.add(currCoinCurrencyPrice);
        }
      }
    }
    return new ArrayList<>(currencyPricesOfCoins);
  }
}
