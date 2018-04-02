package com.crypto.forex.mongo.documents;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CoinPrice {

  private Exchange exchange;

  private Coin basecoin;

  private Coin peggedcoin;

  private Double askPrice;

  private Double bidPrice;

  private Coin originalPeggedCoin;

  private Double originalPeggedAskPrice;

  private Double originalPeggedBidPrice;

  public CoinPrice(final String exchangeId, final String baseCoinSym, final String peggedCoinSym,
      final String originalPeggedCoinSym, final Double bidPrice, final Double askPrice,
      final Double originalPeggedAskPrice, final Double originalPeggedBidPrice) {
    final Exchange newExchange = new Exchange();
    newExchange.setId(exchangeId);
    final Coin baseCoin = new Coin(baseCoinSym, baseCoinSym);
    final Coin peggedCoin = new Coin(peggedCoinSym, peggedCoinSym);
    final Coin originalPeggedCoin = new Coin(originalPeggedCoinSym, originalPeggedCoinSym);
    this.exchange = newExchange;
    this.setBasecoin(baseCoin);
    this.setPeggedcoin(peggedCoin);
    this.setOriginalPeggedCoin(originalPeggedCoin);
    this.askPrice = askPrice;
    this.bidPrice = bidPrice;
    this.originalPeggedBidPrice = originalPeggedBidPrice;
    this.originalPeggedAskPrice = originalPeggedAskPrice;
  }

  public CoinPrice() {}

  public Exchange getExchange() {
    return exchange;
  }

  public void setExchange(final Exchange exchange) {
    this.exchange = exchange;
  }

  public Coin getBasecoin() {
    return basecoin;
  }

  public void setBasecoin(final Coin basecoin) {
    this.basecoin = basecoin;
  }

  public Coin getPeggedcoin() {
    return peggedcoin;
  }

  public void setPeggedcoin(final Coin peggedcoin) {
    this.peggedcoin = peggedcoin;
  }


  public Coin getOriginalPeggedCoin() {
    return originalPeggedCoin;
  }

  public void setOriginalPeggedCoin(final Coin originalPeggedCoin) {
    this.originalPeggedCoin = originalPeggedCoin;
  }

  public Double getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(Double askPrice) {
    this.askPrice = askPrice;
  }

  public Double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(Double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Double getOriginalPeggedAskPrice() {
    return originalPeggedAskPrice;
  }

  public void setOriginalPeggedAskPrice(Double originalPeggedAskPrice) {
    this.originalPeggedAskPrice = originalPeggedAskPrice;
  }

  public Double getOriginalPeggedBidPrice() {
    return originalPeggedBidPrice;
  }

  public void setOriginalPeggedBidPrice(Double originalPeggedBidPrice) {
    this.originalPeggedBidPrice = originalPeggedBidPrice;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }

    final CoinPrice other = (CoinPrice) obj;
    return this.exchange.equals(other.getExchange())
        && this.getBasecoin().equals(other.getBasecoin())
        && this.getPeggedcoin().equals(other.getPeggedcoin());
    // && this.getOriginalPeggedCoin().equals(other.getOriginalPeggedCoin());

  }

  @Override
  public int hashCode() {
    // append(this.originalPeggedCoin)
    return new HashCodeBuilder(17, 37).append(this.exchange).append(this.basecoin)
        .append(this.peggedcoin).build();
  }
}
