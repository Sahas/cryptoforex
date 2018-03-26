package com.crypto.forex.mongo.documents;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "coinPriceData")
public class FullExchangeCoinPriceData {

  @Id
  private ObjectId id;

  private List<ExchangeCoinPrice> exchangescoinprices;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private Date createdtime;

  public List<ExchangeCoinPrice> getExchangescoinprices() {
    return exchangescoinprices;
  }

  public void setExchangescoinprices(final List<ExchangeCoinPrice> exchangescoinprices) {
    this.exchangescoinprices = exchangescoinprices;
  }

  public Date getCreatedtime() {
    return createdtime;
  }

  public void setCreatedtime(final Date createdtime) {
    this.createdtime = createdtime;
  }

  public FullExchangeCoinPriceData() {}

  public FullExchangeCoinPriceData(final List<ExchangeCoinPrice> exchangescoinprices) {
    this.exchangescoinprices = exchangescoinprices;
    this.createdtime = new Date();
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(final ObjectId id) {
    this.id = id;
  }

}
