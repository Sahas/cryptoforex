package com.crypto.forex.exchange.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.crypto.forex.mongo.documents.CoinPrice;
import com.crypto.forex.parse.StringOperations;
import com.crypto.forex.utils.CoinData;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class ExchangeApiManager {
  private static Logger LOGGER = LoggerFactory.getLogger(ExchangeApiManager.class);
  RestTemplate rest = new RestTemplate();

  public CoinPrice getCoinPriceFromExchangeCoinPriceData(
      final ExchangeCoinPriceJson exchangeCoinPrice) {
    if (exchangeCoinPrice.getBaseCoin() != null && exchangeCoinPrice.getPeggedCoin() != null
        && exchangeCoinPrice.getPrice() != null
        && CoinData.coinMap.containsKey(exchangeCoinPrice.getBaseCoin())
        && CoinData.coinMap.containsKey(exchangeCoinPrice.getPeggedCoin())) {
      return new CoinPrice(exchangeCoinPrice.getExchangeId(),
          CoinData.getOverrideCoinValue(exchangeCoinPrice.getBaseCoin()),
          CoinData.getOverrideCoinValue(exchangeCoinPrice.getPeggedCoin()),
          CoinData.getOverrideCoinValue(exchangeCoinPrice.getPeggedCoin()),
          exchangeCoinPrice.getCurrentBidPrice(), exchangeCoinPrice.getCurrentAskPrice(),
          exchangeCoinPrice.getCurrentBidPrice(), exchangeCoinPrice.getCurrentAskPrice());
    }
    else {
      LOGGER.debug("Skipped Coin price creation for :" + exchangeCoinPrice.getBaseCoin() + "-"
          + exchangeCoinPrice.getPeggedCoin());
    }
    return null;
  }

  public List<CoinPrice> getCoinPricesFromResponseData(
      final ExchangeCoinPriceJson[] exchangeCoinPrices) {
    LOGGER.debug("Transforming response data to coin prices");
    final List<CoinPrice> coinPrices = new ArrayList<>();
    for (final ExchangeCoinPriceJson exchangeCoinPrice : exchangeCoinPrices) {
      final CoinPrice coinPrice = getCoinPriceFromExchangeCoinPriceData(exchangeCoinPrice);
      if (coinPrice != null) {
        coinPrices.add(coinPrice);
      }

    }
    return coinPrices;
  }


  public List<CoinPrice> getApiDataOf(final String exchange) {
    LOGGER.debug("Fetching Coin Price for :" + exchange);
    switch (exchange) {
      case "binance":
        return getApiDataOfBinance();
      case "coindelta":
        return getApiDataOfCoindelta();
      case "koinex":
        return getApiDataOfKoinex();
      case "bitbns":
        return getApiDataOfBitbns();
      case "bitfinex":
        return getApiDataOfBitfinex();
      // case "kraken":
      // return getApiDataOfKraken();
      case "hitbtc":
        return getApiDataOfHitbtc();
      default:
        return null;
    }

  }


  public List<CoinPrice> getApiDataOfBinance() {
    final String api = "https://api.binance.com/api/v3/ticker/bookTicker";
    final UriComponents uri = UriComponentsBuilder.fromHttpUrl(api).build();
    final ResponseEntity<BinanceJson[]> response =
        rest.getForEntity(uri.toUriString(), BinanceJson[].class);
    return getCoinPricesFromResponseData(response.getBody());
  }

  public List<CoinPrice> getApiDataOfCoindelta() {
    final String api = "https://coindelta.com/api/v1/public/getticker";
    final UriComponents uri = UriComponentsBuilder.fromHttpUrl(api).build();
    final ResponseEntity<CoindeltaJson[]> response =
        rest.getForEntity(uri.toUriString(), CoindeltaJson[].class);
    return getCoinPricesFromResponseData(response.getBody());
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public List<CoinPrice> getApiDataOfBitbns() {
    final String api = "https://bitbns.com/order/getTickerWithVolume/";
    final UriComponents uri = UriComponentsBuilder.fromHttpUrl(api).build();
    final MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();

    headerMap.add("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
    final RequestEntity entity = new RequestEntity(headerMap, HttpMethod.GET, uri.toUri());
    final ResponseEntity<JsonNode> response = rest.exchange(entity, JsonNode.class);
    final JsonNode exchangeData = response.getBody();
    final List<BitbnsJson> coinData = new ArrayList<>();
    exchangeData.fields().forEachRemaining(row -> {
      final BitbnsJson element = new BitbnsJson();
      element.setBaseCoin(row.getKey());
      element.setPeggedCoin("INR");
      final JsonNode value = row.getValue();
      element.setBidPrice(value.get("highest_buy_bid").asDouble());
      element.setAskPrice(value.get("lowest_sell_bid").asDouble());
      element.setLastPrice(value.get("last_traded_price").asDouble());
      coinData.add(element);
    });
    return getCoinPricesFromResponseData(coinData.toArray(new BitbnsJson[0]));
  }


  @SuppressWarnings({"unchecked", "rawtypes"})
  public List<CoinPrice> getApiDataOfKoinex() {
    final String api = "https://koinex.in/api/ticker";
    final UriComponents uri = UriComponentsBuilder.fromHttpUrl(api).build();
    final MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();

    headerMap.add("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
    final RequestEntity entity = new RequestEntity(headerMap, HttpMethod.GET, uri.toUri());
    final ResponseEntity<JsonNode> response = rest.exchange(entity, JsonNode.class);
    final JsonNode exchangeData = response.getBody().get("stats");
    final List<KoinexJson> coinData = new ArrayList<>();
    exchangeData.fields().forEachRemaining(row -> {
      final KoinexJson element = new KoinexJson();
      element.setBaseCoin(row.getKey());
      element.setPeggedCoin("INR");
      final JsonNode value = row.getValue();
      element.setBidPrice(value.get("highest_bid").asDouble());
      element.setAskPrice(value.get("lowest_ask").asDouble());
      element.setLastPrice(value.get("last_traded_price").asDouble());
      element.setTradedVolume(value.get("vol_24hrs").asDouble());
      coinData.add(element);
    });
    return getCoinPricesFromResponseData(coinData.toArray(new KoinexJson[0]));
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public List<CoinPrice> getApiDataOfBitfinex() {
    final String symbolsApi = "https://api.bitfinex.com/v1/symbols";
    final UriComponents symbolsUri = UriComponentsBuilder.fromHttpUrl(symbolsApi).build();
    final MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
    headerMap.add("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
    final RequestEntity symbolsEntity =
        new RequestEntity(headerMap, HttpMethod.GET, symbolsUri.toUri());
    final ResponseEntity<String[]> symResponse = rest.exchange(symbolsEntity, String[].class);
    final String[] symbols = symResponse.getBody();

    final List<BitfinexJson> coinData = new ArrayList<>();
    final String tickerApi = "https://api.bitfinex.com/v2/tickers";
    final StringBuilder queryParam = new StringBuilder();
    for (final String symbol : symbols) {
      queryParam.append("t").append(symbol.toUpperCase()).append(",");
    }

    final UriComponents tickerUri = UriComponentsBuilder.fromHttpUrl(tickerApi)
        .queryParam("symbols", queryParam.toString()).build();
    final RequestEntity tickerEntity =
        new RequestEntity(headerMap, HttpMethod.GET, tickerUri.toUri());
    final ResponseEntity<String[][]> tickerResponse = rest.exchange(tickerEntity, String[][].class);
    final String[][] tickerData = tickerResponse.getBody();

    for (final String[] coinSpecificData : tickerData) {
      final BitfinexJson element = new BitfinexJson();
      final String rawCoinSym = coinSpecificData[0].substring(1);
      element.setBaseCoin(StringOperations.determineCoin(rawCoinSym, 0));
      element.setPeggedCoin(StringOperations.determineCoin(rawCoinSym, 1));
      element.setBid(Double.valueOf(coinSpecificData[1]));
      element.setAsk(Double.valueOf(coinSpecificData[3]));
      element.setLast(Double.valueOf(coinSpecificData[7]));
      element.setVolume(Double.valueOf(coinSpecificData[8]));
      coinData.add(element);
    }


    return getCoinPricesFromResponseData(coinData.toArray(new BitfinexJson[0]));

  }

  // public List<CoinPrice> getApiDataOfKraken(){
  // final String symbolsApi = "https://api.bitfinex.com/v1/symbols";
  // final UriComponents symbolsUri = UriComponentsBuilder.fromHttpUrl(symbolsApi).build();
  // final MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
  // headerMap.add("User-Agent",
  // "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95
  // Safari/537.11");
  // final RequestEntity symbolsEntity =
  // new RequestEntity(headerMap, HttpMethod.GET, symbolsUri.toUri());
  // final ResponseEntity<String[]> symResponse = rest.exchange(symbolsEntity, String[].class);
  // final String[] symbols = symResponse.getBody();
  // }


  public List<CoinPrice> getApiDataOfHitbtc() {
    final String api = "https://api.hitbtc.com/api/2/public/ticker";
    final UriComponents uri = UriComponentsBuilder.fromHttpUrl(api).build();
    final ResponseEntity<HitbtcJson[]> response =
        rest.getForEntity(uri.toUriString(), HitbtcJson[].class);
    return getCoinPricesFromResponseData(response.getBody());
  }

}
