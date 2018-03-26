package com.crypto.forex.mongo.documents;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Coin {

  @Id
  private String sym;

  private String name;

  public Coin() {}

  public Coin(final String sym, final String name) {
    this.setSym(sym);
    this.name = name;
  }

  public String getSym() {
    return sym;
  }

  public void setSym(final String sym) {
    this.sym = sym.toUpperCase();
  }

  public String getName() {
    return name;
  }  

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(this.sym).build();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    final Coin other = (Coin) obj;
    return this.sym.equals(other.getSym());
  }
}
