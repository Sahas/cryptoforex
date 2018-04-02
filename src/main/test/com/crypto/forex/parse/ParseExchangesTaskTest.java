package com.crypto.forex.parse;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.data.Percentage;
import org.junit.Test;
import com.crypto.forex.mongo.documents.CoinPrice;

public class ParseExchangesTaskTest {

  @Test
  public void getCurrencyPricesOfCoinsShouldReturnCoinPricesInUSDOnlyOneCoinPeggedToCurrency() {
    final ParseExchangesTask parseTask = new ParseExchangesTask();
    final List<CoinPrice> rawCoinPrices = new ArrayList<>();
    rawCoinPrices.add(new CoinPrice("binance", "BTC", "USD", "USD", 8459.25, 8459.25));
    rawCoinPrices.add(new CoinPrice("binance", "TRX", "BTC", "BTC", 0.00000430, 0.00000430));
    rawCoinPrices.add(new CoinPrice("binance", "XRP", "BTC", "BTC", 0.00007455, 0.00007455));
    final List<CoinPrice> coinCurrencyPrices = parseTask.getCurrencyPricesOfCoins(rawCoinPrices);
    final Map<String, CoinPrice> coinPriceMap = new HashMap<>();
    for (final CoinPrice coinCurrencyPrice : coinCurrencyPrices) {
      coinPriceMap.put(coinCurrencyPrice.getBasecoin().getSym(), coinCurrencyPrice);
    }
    assertThat(coinPriceMap.get("BTC").getPeggedcoin().getSym()).isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("TRX").getPeggedcoin().getSym()).isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("TRX").getOriginalPeggedCoin().getSym())
        .isEqualToIgnoringCase("BTC");
    assertThat(coinPriceMap.get("TRX").getOriginalPeggedPrice()).isEqualTo(0.00000430);
    assertThat(coinPriceMap.get("XRP").getPeggedcoin().getSym()).isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("XRP").getPrice()).isCloseTo(0.62, Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("XRP").getOriginalPeggedCoin().getSym())
        .isEqualToIgnoringCase("BTC");
    assertThat(coinPriceMap.get("XRP").getOriginalPeggedPrice()).isEqualTo(0.00007455);
    assertThat(coinPriceMap.get("TRX").getPrice()).isCloseTo(0.0363, Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("BTC").getPrice()).isCloseTo(8459.25, Percentage.withPercentage(2));
  }


  @Test
  public void getCurrencyPricesOfCoinsShouldReturnOnlyCurrencyPeggedCoinPricesIfSomeCoinsAreNotPeggedToCurrency() {
    final ParseExchangesTask parseTask = new ParseExchangesTask();
    final List<CoinPrice> rawCoinPrices = new ArrayList<>();
    rawCoinPrices.add(new CoinPrice("binance", "BTC", "USD", "USD", 8459.25, 8459.25));
    rawCoinPrices.add(new CoinPrice("binance", "TRX", "BTC", "BTC", 0.00000430, 0.00000430));
    rawCoinPrices.add(new CoinPrice("binance", "XRP", "BTC", "BTC", 0.00007455, 0.00007455));
    rawCoinPrices.add(new CoinPrice("binance", "IOTA", "QTUM", "QTUM", 21.08, 21.08));
    final List<CoinPrice> coinCurrencyPrices = parseTask.getCurrencyPricesOfCoins(rawCoinPrices);
    final Map<String, CoinPrice> coinPriceMap = new HashMap<>();
    for (final CoinPrice coinCurrencyPrice : coinCurrencyPrices) {
      coinPriceMap.put(coinCurrencyPrice.getBasecoin().getSym(), coinCurrencyPrice);
    }
    assertThat(coinPriceMap.get("BTC").getPeggedcoin().getSym()).isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("TRX").getPeggedcoin().getSym()).isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("XRP").getPeggedcoin().getSym()).isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("XRP").getPrice()).isCloseTo(0.62, Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("TRX").getPrice()).isCloseTo(0.0363, Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("BTC").getPrice()).isCloseTo(8459.25, Percentage.withPercentage(2));
    assertThat(coinPriceMap.get("IOTA")).isNull();
  }

  @Test
  public void getCurrencyPricesOfCoinsShouldReturnOnlySingleCurrencyPeggedCoinPricesIfSomeCoinsHasTransitivePeggedCurrency() {
    final ParseExchangesTask parseTask = new ParseExchangesTask();
    final List<CoinPrice> rawCoinPrices = new ArrayList<>();
    rawCoinPrices.add(new CoinPrice("binance", "BTC", "USD", 8459.25));
    rawCoinPrices.add(new CoinPrice("binance", "TRX", "BTC", 0.00000430));
    rawCoinPrices.add(new CoinPrice("binance", "XRP", "BTC", 0.00007455));
    rawCoinPrices.add(new CoinPrice("binance", "TRX", "USD", 21.08));
    final List<CoinPrice> coinCurrencyPrices = parseTask.getCurrencyPricesOfCoins(rawCoinPrices);
    final Map<String, CoinPrice> coinPriceMap = new HashMap<>();
    for (final CoinPrice coinCurrencyPrice : coinCurrencyPrices) {
      coinPriceMap.put(coinCurrencyPrice.getBasecoin().getSym(), coinCurrencyPrice);
    }
    assertThat(coinPriceMap.get("BTC").getPeggedcoin().getSym()).isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("TRX").getPeggedcoin().getSym()).isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("XRP").getPeggedcoin().getSym()).isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("XRP").getPrice()).isCloseTo(0.62, Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("TRX").getPrice()).isCloseTo(21.08, Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("BTC").getPrice()).isCloseTo(8459.25, Percentage.withPercentage(2));
  }

  @Test
  public void getCurrencyPricesOfCoinsShouldReturnEveryCurrencyCoinPricesIfSomeCoinCanBeTradedInMultipleCurrencies() {
    final ParseExchangesTask parseTask = new ParseExchangesTask();
    final List<CoinPrice> rawCoinPrices = new ArrayList<>();
    rawCoinPrices.add(new CoinPrice("binance", "BTC", "USD", 8459.25));
    rawCoinPrices.add(new CoinPrice("binance", "BTC", "INR", 498500.25));
    rawCoinPrices.add(new CoinPrice("binance", "TRX", "BTC", 0.00000430));
    rawCoinPrices.add(new CoinPrice("binance", "XRP", "BTC", 0.00007455));
    rawCoinPrices.add(new CoinPrice("binance", "TRX", "USD", 21.08));
    final List<CoinPrice> coinCurrencyPrices = parseTask.getCurrencyPricesOfCoins(rawCoinPrices);
    final Map<String, List<CoinPrice>> coinPriceMap = new HashMap<>();
    for (final CoinPrice coinCurrencyPrice : coinCurrencyPrices) {
      if (coinPriceMap.containsKey(coinCurrencyPrice.getBasecoin().getSym())) {
        coinPriceMap.get(coinCurrencyPrice.getBasecoin().getSym()).add(coinCurrencyPrice);
      } else {
        final List<CoinPrice> currCoinCurrencyPrices = new ArrayList<>();
        currCoinCurrencyPrices.add(coinCurrencyPrice);
        coinPriceMap.put(coinCurrencyPrice.getBasecoin().getSym(), currCoinCurrencyPrices);
      }

    }
    assertThat(coinPriceMap.get("BTC").get(0).getPeggedcoin().getSym())
        .isEqualToIgnoringCase("INR");
    assertThat(coinPriceMap.get("BTC").get(1).getPeggedcoin().getSym())
        .isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("TRX").get(1).getPeggedcoin().getSym())
        .isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("TRX").get(0).getPeggedcoin().getSym())
        .isEqualToIgnoringCase("INR");
    assertThat(coinPriceMap.get("XRP").get(1).getPeggedcoin().getSym())
        .isEqualToIgnoringCase("INR");
    assertThat(coinPriceMap.get("XRP").get(0).getPeggedcoin().getSym())
        .isEqualToIgnoringCase("USD");
    assertThat(coinPriceMap.get("XRP").get(0).getPrice()).isCloseTo(0.62,
        Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("TRX").get(1).getPrice()).isCloseTo(21.08,
        Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("BTC").get(1).getPrice()).isCloseTo(8459.25,
        Percentage.withPercentage(2));
    assertThat(coinPriceMap.get("XRP").get(1).getPrice()).isCloseTo(37.163,
        Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("TRX").get(0).getPrice()).isCloseTo(2.143,
        Percentage.withPercentage(5));
    assertThat(coinPriceMap.get("BTC").get(0).getPrice()).isCloseTo(498500.25,
        Percentage.withPercentage(2));
  }
}
