package com.crypto.forex.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.crypto.forex.mongo.documents.Coin;
import com.crypto.forex.mongo.documents.CoinPrice;
import com.crypto.forex.mongo.documents.Exchange;
import com.crypto.forex.mongo.documents.ExchangeCoinPrice;
import com.crypto.forex.mongo.documents.FullExchangeCoinPriceData;
import com.crypto.forex.repositories.FullExchangeCoinPriceDataRepository;
import com.crypto.forex.utils.ConversionUtils;
import com.fasterxml.jackson.databind.JsonNode;

@RunWith(MockitoJUnitRunner.class)
public class CoinDataHandlerTest {

  @Mock
  Page<FullExchangeCoinPriceData> results;

  @Mock
  private FullExchangeCoinPriceDataRepository exchangeCoinPriceRepository;

  @Mock
  private ConversionUtils conversionUtils;

  @InjectMocks
  CoinDataHandler handler;

  @Test
  public void testThatPercentageCalculationWorksProperly() {
    assertThat(CoinDataHandler.findProfitOrLossPercentage(10000.0, 10000.0)).isEqualTo(0.0);
    assertThat(CoinDataHandler.findProfitOrLossPercentage(5000.0, 10000.0)).isEqualTo(100.0);
    assertThat(CoinDataHandler.findProfitOrLossPercentage(5000.0, 5737.5)).isEqualTo(14.75);
  }

  @Test
  public void getLatestCoinPricesInAllExchangesShouldReturnCoinPricesWithPercentageFields() {
    when(conversionUtils.getExchangePriceFor("USD", "INR")).thenReturn(60.0);
    when(conversionUtils.getExchangePriceFor("INR", "INR")).thenReturn(1.0);
    final Coin normalizedCoin = new Coin("INR", "INR");
    final Map<String, List<CoinPrice>> latestCoinPricesInAllExchanges = new HashMap<>();
    final List<CoinPrice> coinPrices = new ArrayList<>();
    coinPrices.add(new CoinPrice("Binance", "BTC", "USD", 8500.0));
    coinPrices.add(new CoinPrice("Koinex", "BTC", "INR", 600000.0));
    coinPrices.add(new CoinPrice("CoinDelta", "BTC", "INR", 610000.0));
    coinPrices.add(new CoinPrice("GDAX", "BTC", "USD", 8400.0));
    latestCoinPricesInAllExchanges.put("BTC", coinPrices);
    final Set<JsonNode> allNormalizedCoinPricesForBTCSet =
        handler.getCoinPricesInAllExchangesWithNormalizedPrices(latestCoinPricesInAllExchanges,
            normalizedCoin).get("BTC");
    final List<JsonNode> allNormalizedCoinPricesForBTC =
        new ArrayList<>(allNormalizedCoinPricesForBTCSet);
    final JsonNode BinanceNormalizedPrice = allNormalizedCoinPricesForBTC.get(1);
    final JsonNode KoinexNormalizedPrice = allNormalizedCoinPricesForBTC.get(2);
    final JsonNode CoinDeltaNormalizedPrice = allNormalizedCoinPricesForBTC.get(3);
    final JsonNode GDAXNormalizedPrice = allNormalizedCoinPricesForBTC.get(0);

    assertThat(BinanceNormalizedPrice.get("normalizedCoinPrice").asDouble()).isEqualTo(510000.0);
    assertThat(BinanceNormalizedPrice.get("normalizedCoin").get("sym").asText()).isEqualTo("INR");
    assertThat(BinanceNormalizedPrice.get("gainPercent").asDouble()).isEqualTo(1.19);

    assertThat(KoinexNormalizedPrice.get("normalizedCoinPrice").asDouble()).isEqualTo(600000.0);
    assertThat(KoinexNormalizedPrice.get("normalizedCoin").get("sym").asText()).isEqualTo("INR");
    assertThat(KoinexNormalizedPrice.get("gainPercent").asDouble()).isEqualTo(19.05);

    assertThat(CoinDeltaNormalizedPrice.get("normalizedCoinPrice").asDouble()).isEqualTo(610000.0);
    assertThat(CoinDeltaNormalizedPrice.get("normalizedCoin").get("sym").asText()).isEqualTo("INR");
    assertThat(CoinDeltaNormalizedPrice.get("gainPercent").asDouble()).isEqualTo(21.03);

    assertThat(GDAXNormalizedPrice.get("normalizedCoinPrice").asDouble()).isEqualTo(504000.0);
    assertThat(GDAXNormalizedPrice.get("normalizedCoin").get("sym").asText()).isEqualTo("INR");
    assertThat(GDAXNormalizedPrice.get("gainPercent").asDouble()).isEqualTo(0.0);
  }

  @Test
  public void getLatestCoinPricesInAllExchangesShouldReturnCoinDataForAllExchanges(){
    final ExchangeCoinPrice binanceCoinPrices = new ExchangeCoinPrice(
        new Exchange("Binance", "Binance", null), getCoinPricesSameExchange());
    final List<ExchangeCoinPrice> allExchangeCoinPrices = new ArrayList<>();
    allExchangeCoinPrices.add(binanceCoinPrices);
    final FullExchangeCoinPriceData fullData = new FullExchangeCoinPriceData(allExchangeCoinPrices);
    final List<FullExchangeCoinPriceData> fullDataList = new ArrayList<>();
    fullDataList.add(fullData);
    when(exchangeCoinPriceRepository.findAll(Mockito.any(Pageable.class)))
        .thenReturn(results);
    when(results.getContent()).thenReturn(fullDataList);
    final Map<String, List<CoinPrice>> coinDataForBinanceExchange =
        handler.getLatestCoinPricesInAllExchanges();
    assertThat(coinDataForBinanceExchange.size()).isEqualTo(4);
    assertThat(coinDataForBinanceExchange.containsKey("BTC"));
    assertThat(coinDataForBinanceExchange.containsKey("ETH"));
    assertThat(coinDataForBinanceExchange.containsKey("XRP"));
    assertThat(coinDataForBinanceExchange.containsKey("TRX"));
    assertThat(coinDataForBinanceExchange.get("BTC").get(0).getPrice()).isEqualTo(8500.0);
    assertThat(coinDataForBinanceExchange.get("BTC").get(0).getBasecoin().getSym())
        .isEqualTo("BTC");
    assertThat(coinDataForBinanceExchange.get("BTC").get(0).getPeggedcoin().getSym())
        .isEqualTo("USD");
    assertThat(coinDataForBinanceExchange.get("ETH").get(0).getPrice()).isEqualTo(500.0);
    assertThat(coinDataForBinanceExchange.get("TRX").get(0).getPrice()).isEqualTo(0.04);
    assertThat(coinDataForBinanceExchange.get("XRP").get(0).getPrice()).isEqualTo(0.67);
  }

  private List<CoinPrice> getCoinPricesSameCoin() {
    final List<CoinPrice> coinPrices = new ArrayList<>();
    coinPrices.add(new CoinPrice("Binance", "BTC", "USD", 8500.0));
    coinPrices.add(new CoinPrice("Koinex", "BTC", "INR", 600000.0));
    coinPrices.add(new CoinPrice("CoinDelta", "BTC", "INR", 610000.0));
    coinPrices.add(new CoinPrice("GDAX", "BTC", "USD", 8400.0));
    return coinPrices;
  }

  private List<CoinPrice> getCoinPricesSameExchange() {
    final List<CoinPrice> coinPrices = new ArrayList<>();
    coinPrices.add(new CoinPrice("Binance", "BTC", "USD", 8500.0));
    coinPrices.add(new CoinPrice("Binance", "ETH", "USD", 500.0));
    coinPrices.add(new CoinPrice("Binance", "TRX", "USD", 0.04));
    coinPrices.add(new CoinPrice("Binance", "XRP", "USD", 0.67));
    return coinPrices;
  }
}
