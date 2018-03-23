package com.crypto.forex.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.crypto.forex.mongo.documents.CoinPrice;
import com.crypto.forex.mongo.documents.Exchange;

@RestController
public class CoinDataController {

  @Autowired
  private CoinDataHandler handler;

  @RequestMapping(path = "/exchangeCoinPrices", method = {RequestMethod.GET})
  public Map<Exchange, CoinPrice> getCoinPriceDataFromExchanges() {
    return null;
  }
}
