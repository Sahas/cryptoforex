package com.crypto.forex.exchange.api;

public interface ExchangeCoinPriceJson {

  String getExchangeId();

  Double getPrice();

  Double getCurrentAskPrice();

  Double getCurrentBidPrice();

  String getBaseCoin();

  String getPeggedCoin();

  Double getTradedVolume();

}
