package com.crypto.forex.exchange.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.crypto.forex.mongo.documents.CoinPrice;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeApiManagerTest {

  @Mock
  private RestTemplate rest;

  @InjectMocks
  private ExchangeApiManager apiManager;

  private final String bitbnsResponse =
      "{\"BTC\":{\"highest_buy_bid\":581300,\"lowest_sell_bid\":590997,\"last_traded_price\":586000,\"volume\":{\"max\":601504,\"min\":582050,\"rate\":0,\"volume\":4.70577948}},\"XRP\":{\"highest_buy_bid\":43.67,\"lowest_sell_bid\":43.68,\"last_traded_price\":43.67,\"volume\":{\"max\":44.04,\"min\":42.8,\"volume\":199647.2}},\"NEO\":{\"highest_buy_bid\":4510,\"lowest_sell_bid\":4584,\"last_traded_price\":4584,\"volume\":{\"max\":4697.5,\"min\":4467.5,\"volume\":355.7171}},\"GAS\":{\"highest_buy_bid\":1463,\"lowest_sell_bid\":1482,\"last_traded_price\":1463,\"volume\":{\"max\":1521.99,\"min\":1461,\"volume\":1448.9933}},\"ETH\":{\"highest_buy_bid\":35900,\"lowest_sell_bid\":36390,\"last_traded_price\":36399,\"volume\":{\"max\":37500,\"min\":35860,\"volume\":69.3605}},\"XLM\":{\"highest_buy_bid\":16.01,\"lowest_sell_bid\":16.13,\"last_traded_price\":16.07,\"volume\":{\"max\":16.49,\"min\":15.99,\"volume\":138060.58}},\"RPX\":{\"highest_buy_bid\":6.1,\"lowest_sell_bid\":6.26,\"last_traded_price\":6.26,\"volume\":{\"max\":6.42,\"min\":5.91,\"volume\":499127.48}},\"DBC\":{\"highest_buy_bid\":3.1,\"lowest_sell_bid\":3.13,\"last_traded_price\":3.1,\"volume\":{\"max\":3.2,\"min\":3.07,\"volume\":172368.81}},\"LTC\":{\"highest_buy_bid\":10900,\"lowest_sell_bid\":11075,\"last_traded_price\":11075,\"volume\":{\"max\":11430,\"min\":10850,\"volume\":50.2247}},\"XMR\":{\"highest_buy_bid\":13950,\"lowest_sell_bid\":14100,\"last_traded_price\":14100,\"volume\":{\"max\":14390,\"min\":13950,\"volume\":19.5619}},\"DASH\":{\"highest_buy_bid\":27652,\"lowest_sell_bid\":29997,\"last_traded_price\":27600,\"volume\":{\"max\":30511,\"min\":27512.01,\"volume\":6.0937}},\"DOGE\":{\"highest_buy_bid\":0.23,\"lowest_sell_bid\":0.24,\"last_traded_price\":0.24,\"volume\":{\"max\":0.25,\"min\":0.23,\"volume\":3584539}},\"BCH\":{\"highest_buy_bid\":66666,\"lowest_sell_bid\":69998,\"last_traded_price\":66102,\"volume\":{\"max\":70000,\"min\":66102,\"volume\":0.9882}},\"SC\":{\"highest_buy_bid\":0.98,\"lowest_sell_bid\":1,\"last_traded_price\":0.99,\"volume\":{\"max\":0.99,\"min\":0.88,\"volume\":2549157}},\"TRX\":{\"highest_buy_bid\":3.36,\"lowest_sell_bid\":3.37,\"last_traded_price\":3.37,\"volume\":{\"max\":3.43,\"min\":3.24,\"volume\":3281537}},\"ETN\":{\"highest_buy_bid\":2.03,\"lowest_sell_bid\":2.04,\"last_traded_price\":2.03,\"volume\":{\"max\":2.08,\"min\":2,\"volume\":1136638.4}},\"ONT\":{\"highest_buy_bid\":95.1,\"lowest_sell_bid\":97.9,\"last_traded_price\":97.9,\"volume\":{\"max\":98.35,\"min\":94.1,\"volume\":10493.46}},\"ZIL\":{\"highest_buy_bid\":3.67,\"lowest_sell_bid\":3.8,\"last_traded_price\":3.65,\"volume\":{\"max\":3.78,\"min\":3.51,\"volume\":113577}},\"EOS\":{\"highest_buy_bid\":460,\"lowest_sell_bid\":468,\"last_traded_price\":460,\"volume\":{\"max\":488.67,\"min\":455,\"volume\":1688.52}},\"POLY\":{\"highest_buy_bid\":32.9,\"lowest_sell_bid\":33.57,\"last_traded_price\":33.6,\"volume\":{\"max\":35.29,\"min\":32.6,\"volume\":41120.8}},\"DGB\":{\"highest_buy_bid\":1.59,\"lowest_sell_bid\":1.6,\"last_traded_price\":1.59,\"volume\":{\"max\":1.6,\"min\":1.53,\"volume\":1226927}},\"NCASH\":{\"highest_buy_bid\":2.56,\"lowest_sell_bid\":2.57,\"last_traded_price\":2.56,\"volume\":{\"max\":2.75,\"min\":2.45,\"volume\":683892}},\"ADA\":{\"highest_buy_bid\":12.59,\"lowest_sell_bid\":12.71,\"last_traded_price\":12.71,\"volume\":{\"max\":13.21,\"min\":12.53,\"volume\":186566.5}}}";

  private final String koinexResponse =
      "{\"prices\":{\"BTC\":\"588000.0\",\"ETH\":\"35501.0\",\"XRP\":\"43.32\",\"LTC\":\"10900.0\"},\"stats\":{\"ETH\":{\"currency_full_form\":\"ether\",\"currency_short_form\":\"ETH\",\"per_change\":\"-2.066206896551724138\",\"last_traded_price\":35501.0,\"lowest_ask\":\"35708.0\",\"highest_bid\":\"35501.0\",\"min_24hrs\":\"35500.0\",\"max_24hrs\":\"36399.0\",\"vol_24hrs\":174},\"BTC\":{\"currency_full_form\":\"bitcoin\",\"currency_short_form\":\"BTC\",\"per_change\":\"-1.55700652938221999\",\"last_traded_price\":588000.0,\"lowest_ask\":\"589990.0\",\"highest_bid\":\"588000.0\",\"min_24hrs\":\"575500.0\",\"max_24hrs\":\"600000.0\",\"vol_24hrs\":22},\"LTC\":{\"currency_full_form\":\"litecoin\",\"currency_short_form\":\"LTC\",\"per_change\":\"-1.624548736462093863\",\"last_traded_price\":10900.0,\"lowest_ask\":\"10975.0\",\"highest_bid\":\"10901.0\",\"min_24hrs\":\"10700.0\",\"max_24hrs\":\"11149.0\",\"vol_24hrs\":452},\"XRP\":{\"currency_full_form\":\"ripple\",\"currency_short_form\":\"XRP\",\"per_change\":\"-0.413793103448275862068965517\",\"last_traded_price\":43.32,\"lowest_ask\":\"43.48\",\"highest_bid\":\"43.32\",\"min_24hrs\":\"42.86\",\"max_24hrs\":\"43.69\",\"vol_24hrs\":648085}}}";
  
  private final String[][] bitfinexResponse = {
      {"tBTCUSD", "8495", "26.29265024", "8495.1", "40.01458739", "-438.4", "-0.0491", "8495.6",
          "35851.41776595", "8977", "8364.8"},
      {"tLTCUSD", "159.21", "692.94668327", "159.3", "968.40599417", "-5.48", "-0.0333", "159.17",
          "96631.32716752", "166.85", "156.87"},
      {"tLTCBTC", "0.018713", "711.74172714", "0.018745", "817.84661548", "0.000319", "0.0173",
          "0.018749", "5177.67418537", "0.018797", "0.018384"}};
  private final String[] bitfinexSymbols = {"tBTCUSD", "tLTCUSD", "tLTCBTC"};

  @SuppressWarnings("unchecked")
  @Test
  public void apiMangerShouldReturnBinanceDataIfBinanceIsPassed() {
    final BinanceJson[] binanceJsonArr = new BinanceJson[2];
    final BinanceJson bitcoinData = new BinanceJson();
    bitcoinData.setAskPrice("8584.02000000");
    bitcoinData.setBidPrice("8585.00000000");
    bitcoinData.setSymbol("BTCUSDT");
    final BinanceJson ethData = new BinanceJson();
    ethData.setAskPrice("522.61000000");
    ethData.setBidPrice("521.77000000");
    ethData.setSymbol("ETHUSDT");
    binanceJsonArr[0] = bitcoinData;
    binanceJsonArr[1] = ethData;
    
    final Class<ResponseEntity<BinanceJson[]>> responseClass = (Class<ResponseEntity<BinanceJson[]>>)(Class<?>)ResponseEntity.class;
    final ResponseEntity<BinanceJson[]> response = Mockito.mock(responseClass);
    when(rest.getForEntity(any(String.class), any(BinanceJson[].class.getClass())))
        .thenReturn(response);
    when(response.getBody()).thenReturn(binanceJsonArr);
    final List<CoinPrice> binanceData = apiManager.getApiDataOf("binance");
    assertThat(binanceData).isNotEmpty();
    assertThat(binanceData.size()).isEqualTo(2);
    assertThat(binanceData.get(0).getBasecoin().getSym()).isEqualTo("BTC");
    assertThat(binanceData.get(1).getBasecoin().getSym()).isEqualTo("ETH");
    assertThat(binanceData.get(0).getPeggedcoin().getSym()).isEqualTo("USDT");
    assertThat(binanceData.get(1).getPeggedcoin().getSym()).isEqualTo("USDT");
    assertThat(binanceData.get(0).getPrice()).isEqualTo((8584.02000000 + 8585.00000000) / 2);
    assertThat(binanceData.get(1).getPrice()).isEqualTo((522.61000000 + 521.77000000) / 2);

  }


  @SuppressWarnings("unchecked")
  @Test
  public void apiMangerShouldReturnBinanceDataAndNotThrowExcpetionsIfCoinIsInvalid() {
    final BinanceJson[] binanceJsonArr = new BinanceJson[1];
    final BinanceJson bitcoinData = new BinanceJson();
    bitcoinData.setAskPrice("8584.02000000");
    bitcoinData.setBidPrice("8585.00000000");
    bitcoinData.setSymbol("ABCDETUSDT");
    binanceJsonArr[0] = bitcoinData;
    final Class<ResponseEntity<BinanceJson[]>> responseClass =
        (Class<ResponseEntity<BinanceJson[]>>) (Class<?>) ResponseEntity.class;
    final ResponseEntity<BinanceJson[]> response = Mockito.mock(responseClass);
    when(rest.getForEntity(any(String.class), any(BinanceJson[].class.getClass())))
        .thenReturn(response);
    when(response.getBody()).thenReturn(binanceJsonArr);
    final List<CoinPrice> binanceData = apiManager.getApiDataOf("binance");
    assertThat(binanceData).isEmpty();
  }

  @SuppressWarnings("unchecked")
  @Test
  public void apiManagerShouldReturnBitbnsDataIfBitbnsIsPassed()
      throws JsonParseException, JsonMappingException, IOException {
    final ResponseEntity<JsonNode> response =
        Mockito.mock((Class<ResponseEntity<JsonNode>>) (Class<?>) ResponseEntity.class);
    final ObjectMapper mapper = new ObjectMapper();
    final JsonNode responseJson = mapper.readValue(bitbnsResponse, JsonNode.class);
    when(response.getBody()).thenReturn(responseJson);
    when(rest.exchange(any(RequestEntity.class), any(JsonNode.class.getClass())))
        .thenReturn(response);
    final List<CoinPrice> bitbnsData = apiManager.getApiDataOf("bitbns");
    assertThat(bitbnsData).isNotEmpty();
    assertThat(bitbnsData.size()).isEqualTo(23);
    assertThat(bitbnsData.get(0).getBasecoin().getSym()).isEqualTo("BTC");
    assertThat(bitbnsData.get(0).getPeggedcoin().getSym()).isEqualTo("INR");
    assertThat(bitbnsData.get(0).getPrice()).isEqualTo((581300.0 + 590997.0) / 2);
    assertThat(bitbnsData.get(1).getBasecoin().getSym()).isEqualTo("XRP");
    assertThat(bitbnsData.get(1).getPeggedcoin().getSym()).isEqualTo("INR");
    assertThat(bitbnsData.get(1).getPrice()).isEqualTo((43.67 + 43.68) / 2);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void apiManagerShouldReturnKoinexDataIdKoinexIsPassed()
      throws JsonParseException, JsonMappingException, IOException {
    final ResponseEntity<JsonNode> response =
        Mockito.mock((Class<ResponseEntity<JsonNode>>) (Class<?>) ResponseEntity.class);
    final ObjectMapper mapper = new ObjectMapper();
    final JsonNode responseJson = mapper.readValue(koinexResponse, JsonNode.class);
    when(response.getBody()).thenReturn(responseJson);
    when(rest.exchange(any(RequestEntity.class), any(JsonNode.class.getClass())))
        .thenReturn(response);
    final List<CoinPrice> koinexData = apiManager.getApiDataOf("koinex");
    assertThat(koinexData).isNotEmpty();
    assertThat(koinexData.size()).isEqualTo(4);
    assertThat(koinexData.get(0).getBasecoin().getSym()).isEqualTo("ETH");
    assertThat(koinexData.get(0).getPeggedcoin().getSym()).isEqualTo("INR");
    assertThat(koinexData.get(0).getPrice()).isEqualTo((35708.0 + 35501.0) / 2);
    assertThat(koinexData.get(1).getBasecoin().getSym()).isEqualTo("BTC");
    assertThat(koinexData.get(1).getPeggedcoin().getSym()).isEqualTo("INR");
    assertThat(koinexData.get(1).getPrice()).isEqualTo((589990.0 + 588000.0) / 2);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void apiManagerShouldReturnBitfinexDataIdBitfinexIsPassed()
      throws JsonParseException, JsonMappingException, IOException {
    
    final Class<String[][]> twodarrClass = (Class<String[][]>)(Class<?>) String[][].class;
    final Class<String[]> arrClass = (Class<String[]>) (Class<?>) String[].class;
    final ResponseEntity<String[][]> tickerResponse =
        Mockito.mock((Class<ResponseEntity<String[][]>>) (Class<?>) ResponseEntity.class);
    final ResponseEntity<String[]> symbolResponse =
        Mockito.mock((Class<ResponseEntity<String[]>>) (Class<?>) ResponseEntity.class);
    when(symbolResponse.getBody()).thenReturn(bitfinexSymbols);
    when(tickerResponse.getBody()).thenReturn(bitfinexResponse);
    when(rest.exchange(any(RequestEntity.class), twodarrClass))
        .thenReturn(tickerResponse);
    when(rest.exchange(any(RequestEntity.class), arrClass))
        .thenReturn(symbolResponse);
    final List<CoinPrice> bitfinexData = apiManager.getApiDataOf("bitfinex");
    assertThat(bitfinexData).isNotEmpty();
    assertThat(bitfinexData.size()).isEqualTo(3);
    assertThat(bitfinexData.get(0).getBasecoin().getSym()).isEqualTo("BTC");
    assertThat(bitfinexData.get(0).getPeggedcoin().getSym()).isEqualTo("USD");
    assertThat(bitfinexData.get(0).getPrice()).isEqualTo((8495.0 + 8495.1) / 2);
    assertThat(bitfinexData.get(1).getBasecoin().getSym()).isEqualTo("LTC");
    assertThat(bitfinexData.get(1).getPeggedcoin().getSym()).isEqualTo("USD");
    assertThat(bitfinexData.get(1).getPrice()).isEqualTo((159.21 + 159.3) / 2);
  }
}
