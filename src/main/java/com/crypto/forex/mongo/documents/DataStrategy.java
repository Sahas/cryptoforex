package com.crypto.forex.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DataStrategy {
  
  @Id
  private Exchange exchange;
  
  private DataStrategyTypes stategyType;
  
  private String priceApi;
  
  private String feesApi;
  
  private String scrapingPageUri; 
  
}
