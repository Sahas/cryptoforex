package com.crypto.forex.utils;

public enum Currencies {
  USD("USD"), INR("INR");
  
  private String symbol;
  
  Currencies(String symbol){
    this.setSymbol(symbol);
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

}
