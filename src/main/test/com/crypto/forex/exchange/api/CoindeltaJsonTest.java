package com.crypto.forex.exchange.api;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CoindeltaJsonTest {

  private static final String coindeltaResponseStr =
      "[{\"Ask\":619989.7,\"Bid\":610000.0,\"MarketName\":\"btc-inr\",\"Last\":600011.0},{\"Ask\":38000.0,\"Bid\":37950.0,\"MarketName\":\"eth-inr\",\"Last\":37949.0},{\"Ask\":11399.99,\"Bid\":11380.0,\"MarketName\":\"ltc-inr\",\"Last\":11399.99},{\"Ask\":812.0,\"Bid\":815.0,\"MarketName\":\"omg-inr\",\"Last\":849.0},{\"Ask\":1394.0,\"Bid\":1380.0,\"MarketName\":\"qtum-inr\",\"Last\":1380.0},{\"Ask\":0.08489997,\"Bid\":0.00696001,\"MarketName\":\"eth-btc\",\"Last\":0.0849999},{\"Ask\":0.02299899,\"Bid\":0.00111283,\"MarketName\":\"ltc-btc\",\"Last\":0.02347996},{\"Ask\":0.00179897,\"Bid\":0.00033045,\"MarketName\":\"omg-btc\",\"Last\":0.00127},{\"Ask\":0.0025,\"Bid\":0.00060001,\"MarketName\":\"qtum-btc\",\"Last\":0.00206},{\"Ask\":47.49,\"Bid\":47.16,\"MarketName\":\"xrp-inr\",\"Last\":47.16},{\"Ask\":8.689e-05,\"Bid\":6.528e-05,\"MarketName\":\"xrp-btc\",\"Last\":8.775e-05},{\"Ask\":71850.0,\"Bid\":69000.0,\"MarketName\":\"bch-inr\",\"Last\":71849.0},{\"Ask\":3.39,\"Bid\":3.22,\"MarketName\":\"zil-inr\",\"Last\":3.2},{\"Ask\":39.89,\"Bid\":37.0,\"MarketName\":\"zrx-inr\",\"Last\":39.97}]";

  private static final String bitbnsResponseStr =
      "{\"BTC\":{\"highest_buy_bid\":585175,\"lowest_sell_bid\":587990,\"last_traded_price\":585175,\"volume\":{\"max\":601504,\"min\":585150,\"rate\":\"585175.00\",\"volume\":4.7912893}},\"XRP\":{\"highest_buy_bid\":43.31,\"lowest_sell_bid\":43.67,\"last_traded_price\":43.31,\"volume\":{\"max\":44.04,\"min\":42.8,\"volume\":206354.85}},\"NEO\":{\"highest_buy_bid\":4501.01,\"lowest_sell_bid\":4594,\"last_traded_price\":4594,\"volume\":{\"max\":4699,\"min\":4500,\"volume\":346.4957}},\"GAS\":{\"highest_buy_bid\":1466,\"lowest_sell_bid\":1482,\"last_traded_price\":1482,\"volume\":{\"max\":1523.98,\"min\":1461,\"volume\":1482.2778}},\"ETH\":{\"highest_buy_bid\":35900,\"lowest_sell_bid\":36499,\"last_traded_price\":36499,\"volume\":{\"max\":37500,\"min\":35860,\"volume\":74.9629}},\"XLM\":{\"highest_buy_bid\":16.2,\"lowest_sell_bid\":16.25,\"last_traded_price\":16.2,\"volume\":{\"max\":16.49,\"min\":15.91,\"volume\":141820.79}},\"RPX\":{\"highest_buy_bid\":5.93,\"lowest_sell_bid\":6.1,\"last_traded_price\":5.92,\"volume\":{\"max\":6.25,\"min\":5.76,\"volume\":432828.32}},\"DBC\":{\"highest_buy_bid\":3.08,\"lowest_sell_bid\":3.1,\"last_traded_price\":3.1,\"volume\":{}},\"LTC\":{\"highest_buy_bid\":10900,\"lowest_sell_bid\":11100,\"last_traded_price\":10900,\"volume\":{}},\"XMR\":{\"highest_buy_bid\":13950,\"lowest_sell_bid\":14100,\"last_traded_price\":13950,\"volume\":{}},\"DASH\":{\"highest_buy_bid\":27601,\"lowest_sell_bid\":29998.99,\"last_traded_price\":27600,\"volume\":{\"max\":30511,\"min\":27512.01,\"volume\":6.0937}},\"DOGE\":{\"highest_buy_bid\":0.23,\"lowest_sell_bid\":0.24,\"last_traded_price\":0.24,\"volume\":{\"max\":0.25,\"min\":0.23,\"volume\":3388649}},\"BCH\":{\"highest_buy_bid\":66102,\"lowest_sell_bid\":69999,\"last_traded_price\":66102,\"volume\":{\"max\":70000,\"min\":66102,\"volume\":1.0082}},\"SC\":{\"highest_buy_bid\":0.92,\"lowest_sell_bid\":0.93,\"last_traded_price\":0.93,\"volume\":{\"max\":0.92,\"min\":0.87,\"volume\":2000652}},\"TRX\":{\"highest_buy_bid\":3.35,\"lowest_sell_bid\":3.39,\"last_traded_price\":3.37,\"volume\":{\"max\":3.48,\"min\":3.04,\"volume\":3134015}},\"ETN\":{\"highest_buy_bid\":2.03,\"lowest_sell_bid\":2.04,\"last_traded_price\":2.04,\"volume\":{}},\"ONT\":{\"highest_buy_bid\":94.1,\"lowest_sell_bid\":96,\"last_traded_price\":94.1,\"volume\":{}},\"ZIL\":{\"highest_buy_bid\":3.65,\"lowest_sell_bid\":3.8,\"last_traded_price\":3.66,\"volume\":{\"max\":3.78,\"min\":3.5,\"volume\":114397}},\"EOS\":{\"highest_buy_bid\":465,\"lowest_sell_bid\":469.99,\"last_traded_price\":470,\"volume\":{\"max\":488.67,\"min\":460,\"volume\":1890.54}},\"POLY\":{\"highest_buy_bid\":32.5,\"lowest_sell_bid\":33.99,\"last_traded_price\":32.33,\"volume\":{}},\"DGB\":{\"highest_buy_bid\":1.55,\"lowest_sell_bid\":1.57,\"last_traded_price\":1.55,\"volume\":{}},\"NCASH\":{\"highest_buy_bid\":2.43,\"lowest_sell_bid\":2.58,\"last_traded_price\":2.6,\"volume\":{\"max\":2.75,\"min\":2.45,\"volume\":674930}},\"ADA\":{\"highest_buy_bid\":12.55,\"lowest_sell_bid\":12.6,\"last_traded_price\":12.65,\"volume\":{\"max\":13.21,\"min\":12.4,\"volume\":169051.2}}}";

  @Test
  public void coinDeltaJsonShouldBeCorrectlyDeserializedByObjectMapper()
      throws JsonParseException, JsonMappingException, IOException {
    final ObjectMapper mapper = new ObjectMapper();
    final List<CoindeltaJson> json =
        mapper.readValue(coindeltaResponseStr, new TypeReference<List<CoindeltaJson>>() {});
    assertThat(json.get(0).getMarketName()).isNotBlank();
  }
}
