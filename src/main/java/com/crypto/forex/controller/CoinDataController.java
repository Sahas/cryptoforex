package com.crypto.forex.controller;

import java.util.Arrays;
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

  @RequestMapping(path = "/allExchangeCoinPrices", method = {RequestMethod.GET})
  public Map<String, Set<JsonNode>> getCoinPriceDataOfExchanges(
      @RequestParam final String baseCurrency) {
    return handler.getCoinPricesInAllExchangesWithNormalizedPrices(
        handler.getLatestCoinPricesOfAllExchanges(), new Coin(baseCurrency, baseCurrency));
  }

  @RequestMapping(path = "/getBestTransferDealBetweenExchanges", method = {RequestMethod.GET})
  public Map<String, Set<JsonNode>> getBestCoinPriceDataOfExchanges(
      @RequestParam final String from, @RequestParam final String to,
      @RequestParam final String baseCurrency) {
    return handler.getCoinPricesInAllExchangesWithNormalizedPrices(
        handler.getLatestCoinPricesInExchanges(Arrays.asList(from, to)),
        new Coin(baseCurrency, baseCurrency));
  }
}
