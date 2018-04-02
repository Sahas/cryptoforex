package com.crypto.forex.exchange.api;

import com.crypto.forex.parse.StringOperations;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ExchangeApi(exchange = "binance")
public class BinanceJson extends AbstractExchangeCoinPriceJson {

  @JsonProperty("askPrice")
  private String askPrice;

  @JsonProperty("bidPrice")
  private String bidPrice;

  @JsonProperty("symbol")
  private String symbol;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(final String symbol) {
    this.symbol = symbol;
  }

  public String getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(final String askPrice) {
    this.askPrice = askPrice;
  }

  public String getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(final String bidPrice) {
    this.bidPrice = bidPrice;
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
    return null;
  }

  @Override
  public Double getPrice() {
    final Double askPrice = Double.valueOf(this.askPrice);
    final Double bidPrice = Double.valueOf(this.bidPrice);
    return (askPrice + bidPrice) / 2;
  }

  @Override
  public Double getCurrentAskPrice() {
    return Double.valueOf(this.askPrice);
  }

  @Override
  public Double getCurrentBidPrice() {
    return Double.valueOf(this.bidPrice);
  }

}
