package com.crypto.forex.exchange.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ExchangeApi(exchange = "bitfinex")
public class BitfinexJson extends AbstractExchangeCoinPriceJson {

  private String baseCoin;

  private String peggedCoin;

  @JsonProperty("bid")
  private Double bid;

  @JsonProperty("ask")
  private Double ask;

  @JsonProperty("last_price")
  private Double last;

  @JsonProperty("volume")
  private Double volume;


  @Override
  public Double getPrice() {
    return (bid + ask) / 2;
  }

  @Override
  public String getBaseCoin() {
    return this.baseCoin;
  }

  @Override
  public String getPeggedCoin() {
    return this.peggedCoin;
  }

  @Override
  public Double getTradedVolume() {
    return this.volume;
  }

  public Double getBid() {
    return bid;
  }

  public void setBid(final Double bid) {
    this.bid = bid;
  }

  public Double getAsk() {
    return ask;
  }

  public void setAsk(final Double ask) {
    this.ask = ask;
  }

  public Double getLast() {
    return last;
  }

  public void setLast(final Double last) {
    this.last = last;
  }

  public Double getVolume() {
    return volume;
  }

  public void setVolume(final Double volume) {
    this.volume = volume;
  }

  public void setBaseCoin(final String baseCoin) {
    this.baseCoin = baseCoin;
  }

  public void setPeggedCoin(final String peggedCoin) {
    this.peggedCoin = peggedCoin;
  }
}
