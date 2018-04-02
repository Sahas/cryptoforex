package com.crypto.forex.mongo.documents;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "exchangeCoinPrices")
public class ExchangeCoinPrice {

  @Id
  private ObjectId id;

  @Indexed
  private Exchange exchange;

  private List<CoinPrice> coinprices;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private Date createddate;

  public ObjectId getId() {
    return id;
  }

  public void setId(final ObjectId id) {
    this.id = id;
  }

  public Date getCreateddate() {
    return createddate;
  }

  public void setCreateddate(final Date createddate) {
    this.createddate = createddate;
  }

  public ExchangeCoinPrice() {}

  public ExchangeCoinPrice(final Exchange exchange, final List<CoinPrice> coinprices) {
    this.exchange = exchange;
    this.coinprices = coinprices;
  }

  public Exchange getExchange() {
    return exchange;
  }

  public void setExchange(final Exchange exchange) {
    this.exchange = exchange;
  }

  public List<CoinPrice> getCoinprices() {
    return coinprices;
  }

  public void setCoinprices(final List<CoinPrice> coinprices) {
    this.coinprices = coinprices;
  }

  public void removeCoinprices(Predicate<? super CoinPrice> filter) {
    this.coinprices.removeIf(filter);
  }

  public void removeCoinprices(List<String> exchangeIds) {

  }

  public Set<String> getTradedCoins() {
    Set<String> tradedCoins = new HashSet<>();
    if (this.coinprices == null || this.coinprices.isEmpty()) {
      return tradedCoins;
    }
    for (CoinPrice coinprice : this.coinprices) {
      tradedCoins.add(coinprice.getBasecoin().getSym());
    }
    return tradedCoins;
  }
}
