package com.crypto.forex.mongo.documents;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CoinPrice {

  private Exchange exchange;

  private Coin basecoin;

  private Coin peggedcoin;

  private Double price;

  private Coin originalPeggedCoin;

  private Double originalPeggedPrice;

  public CoinPrice(final String exchangeId, final String baseCoinSym, final String peggedCoinSym,
      final String originalPeggedCoinSym, final Double price, final Double originalPeggedPrice) {
    final Exchange exchange = new Exchange();
    exchange.setId(exchangeId);
    final Coin baseCoin = new Coin(baseCoinSym, baseCoinSym);
    final Coin peggedCoin = new Coin(peggedCoinSym, peggedCoinSym);
    final Coin originalPeggedCoin = new Coin(originalPeggedCoinSym, originalPeggedCoinSym);
    this.exchange = exchange;
    this.setBasecoin(baseCoin);
    this.setPeggedcoin(peggedCoin);
    this.setOriginalPeggedCoin(originalPeggedCoin);
    this.price = price;
    this.originalPeggedPrice = originalPeggedPrice;
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


  public Coin getOriginalPeggedCoin() {
    return originalPeggedCoin;
  }

  public void setOriginalPeggedCoin(final Coin originalPeggedCoin) {
    this.originalPeggedCoin = originalPeggedCoin;
  }

  public Double getOriginalPeggedPrice() {
    return originalPeggedPrice;
  }

  public void setOriginalPeggedPrice(final Double originalPeggedPrice) {
    this.originalPeggedPrice = originalPeggedPrice;
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
