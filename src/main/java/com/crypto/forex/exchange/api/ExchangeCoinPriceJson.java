package com.crypto.forex.exchange.api;

public interface ExchangeCoinPriceJson {

  String getExchangeId();

  Double getPrice();

  String getBaseCoin();

  String getPeggedCoin();

  Double getTradedVolume();

}
