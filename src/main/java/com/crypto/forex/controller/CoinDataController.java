package com.crypto.forex.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crypto.forex.mongo.documents.Coin;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class CoinDataController {

  @Autowired
  private CoinDataHandler handler;

  @RequestMapping(path = "/exchangeCoinPrices", method = {RequestMethod.GET})
  public Map<String, Set<JsonNode>> getCoinPriceDataOfExchanges(
      @RequestParam final List<String> exchanges,
      @RequestParam final String baseCurrency) {
    return handler.getCoinPricesInAllExchangesWithNormalizedPrices(
        handler.getLatestCoinPricesInExchanges(null), new Coin(baseCurrency, baseCurrency));
  }
}
