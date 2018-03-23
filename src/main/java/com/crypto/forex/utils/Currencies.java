package com.crypto.forex.utils;

public enum Currencies {
  USD("USD"), INR("INR"), USDT("USDT");
  
  private String symbol;
  
  Currencies(final String symbol){
    this.setSymbol(symbol);
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(final String symbol) {
    this.symbol = symbol;
  }

  public static boolean isCurrency(final String symbol) {
    for (final Currencies currency : Currencies.values()) {
      if (currency.getSymbol().equalsIgnoreCase(symbol)) {
        return true;
      }
    }
    return false;
  }

}
