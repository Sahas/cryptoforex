package com.crypto.forex.mongo.documents;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CoinPrice {

  private Exchange exchange;

  private Coin basecoin;

  private Coin peggedcoin;

  private Double price;

  public CoinPrice(final String exchangeId, final String baseCoinSym, final String peggedCoinSym,
      final Double price) {
    final Exchange exchange = new Exchange();
    exchange.setId(exchangeId);
    final Coin baseCoin = new Coin(baseCoinSym, baseCoinSym);
    final Coin peggedCoin = new Coin(peggedCoinSym, peggedCoinSym);
    this.exchange = exchange;
    this.setBasecoin(baseCoin);
    this.setPeggedcoin(peggedCoin);
    this.price = price;
  }

  public CoinPrice() {}

  public Exchange getExchange() {
    return exchange;
  }

  public void setExchange(final Exchange exchange) {
    this.exchange = exchange;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(final Double price) {
    this.price = price;
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

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }

    final CoinPrice other = (CoinPrice) obj;
    return this.exchange.equals(other.getExchange())
        && this.getBasecoin().equals(other.getBasecoin())
        && this.getPeggedcoin().equals(other.getPeggedcoin());

  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(this.exchange).append(this.basecoin)
        .append(this.peggedcoin).build();
  }
}
