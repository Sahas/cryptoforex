package com.crypto.forex.exchange.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ExchangeApi(exchange = "coindelta")
public class CoindeltaJson extends AbstractExchangeCoinPriceJson {

  @JsonProperty("Last")
  private Double last;

  @JsonProperty("MarketName")
  private String marketName;

  @JsonProperty("Ask")
  private Double ask;

  @JsonProperty("Bid")
  private Double bid;

  public Double getLast() {
    return last;
  }

  public void setLast(final Double last) {
    this.last = last;
  }

  public String getMarketName() {
    return marketName;
  }

  public void setMarketName(final String marketName) {
    this.marketName = marketName;
  }

  public Double getAsk() {
    return ask;
  }

  public void setAsk(final Double ask) {
    this.ask = ask;
  }

  public Double getBid() {
    return bid;
  }

  public void setBid(final Double bid) {
    this.bid = bid;
  }

  @Override
  public Double getPrice() {
    return bid;
  }

  @Override
  public String getBaseCoin() {
    return this.getMarketName().split("-")[0].toUpperCase();
  }

  @Override
  public String getPeggedCoin() {
    return this.getMarketName().split("-")[1].toUpperCase();
  }

  @Override
  public Double getTradedVolume() {
    return null;
  }
}
