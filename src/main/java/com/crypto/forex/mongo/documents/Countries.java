package com.crypto.forex.mongo.documents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;

@XmlEnum()
@XmlAccessorType(XmlAccessType.FIELD)
public enum Countries {
  USA("USA", "USD"), INDIA("India", "INR");
  
  @XmlElement(required = true)
  private String name;

  @XmlElement(required = true)
  private String currency;
  
  private Countries(final String name, final String currency){
    this.setCurrency(currency);
    this.setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(final String currency) {
    this.currency = currency;
  }
}
