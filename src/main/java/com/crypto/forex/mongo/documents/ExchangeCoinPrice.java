package com.crypto.forex.mongo.documents;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "exchangeCoinPrices")
public class ExchangeCoinPrice {

  @Id
  private ObjectId id;

  @Indexed
  @DBRef
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
}
