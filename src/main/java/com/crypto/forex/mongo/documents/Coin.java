package com.crypto.forex.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Coin {
  
  @Id
  private final String sym;
  private final String name;
  
  public Coin(String sym, String name) {
    this.sym = sym;
    this.name = name;
  }

  public String getSym() {
    return sym;
  }

  public String getName() {
    return name;
  }  
}
