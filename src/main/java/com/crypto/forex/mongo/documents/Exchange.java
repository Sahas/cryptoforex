package com.crypto.forex.mongo.documents;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="exchanges")
public class Exchange {
  
  @Id
  private String id;
  
  private String name;
  
  private List<Countries> tradingCountries;
  
  private List<Coin> tradedCoins;
  
  private List<CoinPrice> coinPrices;
  
}
