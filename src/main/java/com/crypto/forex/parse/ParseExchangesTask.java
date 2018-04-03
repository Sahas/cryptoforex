package com.crypto.forex.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.crypto.forex.exchange.api.ExchangeApiManager;
import com.crypto.forex.mongo.documents.Coin;
import com.crypto.forex.mongo.documents.CoinPrice;
import com.crypto.forex.mongo.documents.ExchangeCoinPrice;
import com.crypto.forex.mongo.documents.FullExchangeCoinPriceData;
import com.crypto.forex.repositories.FullExchangeCoinPriceDataRepository;
import com.crypto.forex.utils.Currencies;

@Component
public class ParseExchangesTask {
  private static final Logger LOGGER = LoggerFactory.getLogger(ParseExchangesTask.class);

  @Autowired
  private FullExchangeCoinPriceDataRepository repository;

  @Autowired
  private List<String> availableExchanges;

  @Autowired
  ExchangeApiManager exchangeApiManager;

  @Scheduled(cron = "0 0/10 * * * ?")
  public void fetchAndPutAllExchangeDataInDB() {
    final List<ExchangeCoinPrice> allExchangeCoinPrices = new ArrayList<>();
    for (final String exchangeId : availableExchanges) {
      LOGGER.debug("Exchange Id : " + exchangeId);
      final List<CoinPrice> coinPrices = exchangeApiManager.getApiDataOf(exchangeId);
      final List<CoinPrice> currencyPrices = getCurrencyPricesOfCoins(coinPrices);
      allExchangeCoinPrices
          .add(new ExchangeCoinPrice(currencyPrices.get(0).getExchange(), currencyPrices));
    }
    LOGGER.debug("Storing Coin Prices in DB");
    repository.save(new FullExchangeCoinPriceData(allExchangeCoinPrices));
  }

  public List<CoinPrice> getCurrencyPricesOfCoins(final List<CoinPrice> rawCoinPrices) {
    LOGGER.debug("Calculating currency prices for coins");
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
      LOGGER.debug("Fetching currency prices for coin : " + rawCoinPrice.getBasecoin().getSym());
      if (rawCoinPrice.getAskPrice() == null || rawCoinPrice.getBidPrice() == null) {
        continue;
      }
      if (coinCurrencyPriceMap.containsKey(peggedCoin)) {
        for (final CoinPrice peggedCoinCurrencyPrice : coinCurrencyPriceMap.get(peggedCoin)) {
          final Double currencyAskPriceValue =
              rawCoinPrice.getAskPrice() * peggedCoinCurrencyPrice.getAskPrice();
          final Double currencyBidPriceValue =
              rawCoinPrice.getBidPrice() * peggedCoinCurrencyPrice.getBidPrice();
          final CoinPrice currCoinCurrencyPrice =
              new CoinPrice(rawCoinPrice.getExchange().getId(), rawCoinPrice.getBasecoin().getSym(),
                  peggedCoinCurrencyPrice.getPeggedcoin().getSym(),
                  rawCoinPrice.getPeggedcoin().getSym(), currencyBidPriceValue,
                  currencyAskPriceValue,
                  currencyBidPriceValue, currencyAskPriceValue);
          currencyPricesOfCoins.add(currCoinCurrencyPrice);
        }
      }
    }
    return new ArrayList<>(currencyPricesOfCoins);
  }

  @PostConstruct
  public void onStartup() {
    fetchAndPutAllExchangeDataInDB();
  }
}
