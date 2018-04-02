package com.crypto.forex.exchange.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
// @ExchangeApi(exchange = "kraken")
public class KrakenJson extends AbstractExchangeCoinPriceJson {

  @Override
  public Double getPrice() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getBaseCoin() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getPeggedCoin() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Double getTradedVolume() {
    // TODO Auto-generated method stub
    return null;
  }

}
