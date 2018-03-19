package com.crypto.forex.mongo.documents;

public enum Countries {
  USA("USA", "USD"), INDIA("India", "INR");
  
  private String name;
  private String currency;
  
  private Countries(String name, String currency){
    this.setCurrency(currency);
    this.setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
