package com.crypto.forex.mongo.documents;

import java.util.Map;

public class CoinPrice {
  
  private Exchange exchange;
  
  private Coin coin;
  
  private Map<Coin, Double> peggedPrices;
  
  public CoinPrice() {}

  public Coin getCoin() {
    return coin;
  }
  public void setCoin(final Coin coin) {
    this.coin = coin;
  }
  
}
