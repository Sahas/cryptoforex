package com.crypto.forex.exchange.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@ExchangeApi(exchange = "koinex")
public class KoinexJson extends AbstractExchangeCoinPriceJson {

  private String baseCoin;
  private String peggedCoin;
  private Double bidPrice;
  private Double askPrice;
  private Double lastPrice;
  private Double tradedVolume;



  @Override
  public Double getPrice() {
    return bidPrice;
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
    return this.tradedVolume;
  }

  public Double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(final Double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Double getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(final Double askPrice) {
    this.askPrice = askPrice;
  }

  public Double getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(final Double lastPrice) {
    this.lastPrice = lastPrice;
  }

  public void setBaseCoin(final String baseCoin) {
    this.baseCoin = baseCoin;
  }

  public void setPeggedCoin(final String peggedCoin) {
    this.peggedCoin = peggedCoin;
  }

  public void setTradedVolume(final Double tradedVolume) {
    this.tradedVolume = tradedVolume;
  }

  @Override
  public Double getCurrentAskPrice() {
    return this.askPrice;
  }

  @Override
  public Double getCurrentBidPrice() {
    return this.bidPrice;
  }

}
