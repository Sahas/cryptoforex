package com.crypto.forex.mongo.documents;

import java.util.Map;

public class CoinPrice {
  
  private Exchange exchange;
  
  private Coin coin;
  
  private Map<Coin, Double> peggedPrices;
  
  public Coin getCoin() {
    return coin;
  }
  public void setCoin(Coin coin) {
    this.coin = coin;
  }
  
}
