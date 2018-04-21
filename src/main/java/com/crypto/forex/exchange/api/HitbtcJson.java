package com.crypto.forex.exchange.api;

import com.crypto.forex.parse.StringOperations;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ExchangeApi(exchange = "hitbtc")
public class HitbtcJson extends AbstractExchangeCoinPriceJson {

  private String ask;
  private String bid;
  private String last;
  @JsonProperty("volumeQuote")
  private String volume;
  private String symbol;

  @Override
  public Double getPrice() {
    if (ask == null) {
      return null;
    }
    return Double.valueOf(ask);
  }

  @Override
  public String getBaseCoin() {
    return StringOperations.determineCoin(this.symbol, 0);
  }

  @Override
  public String getPeggedCoin() {
    return StringOperations.determineCoin(this.symbol, 1);
  }

  @Override
  public Double getTradedVolume() {
    return Double.valueOf(volume);
  }

  public String getAsk() {
    return ask;
  }

  public void setAsk(final String ask) {
    this.ask = ask;
  }

  public String getBid() {
    return bid;
  }

  public void setBid(final String bid) {
    this.bid = bid;
  }

  public String getLast() {
    return last;
  }

  public void setLast(final String last) {
    this.last = last;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(final String volume) {
    this.volume = volume;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(final String symbol) {
    this.symbol = symbol;
  }

  @Override
  public Double getCurrentAskPrice() {
    if (ask == null) {
      return null;
    }
    return Double.valueOf(ask);
  }

  @Override
  public Double getCurrentBidPrice() {
    if (bid == null) {
      return null;
    }
    return Double.valueOf(bid);
  }

}
