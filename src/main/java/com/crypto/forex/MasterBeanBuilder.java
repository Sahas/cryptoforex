package com.crypto.forex;

import java.util.List;
import com.crypto.forex.mongo.documents.Exchange;
import com.crypto.forex.parse.ExchangeParseDef;

public class MasterBeanBuilder {
  private MasterBeanImpl masterBean;
  private List<Exchange> exchanges;
  private List<ExchangeParseDef> exchangeParseDefs;

  public MasterBeanBuilder withExchanges(final List<Exchange> exchanges) {
    this.exchanges = exchanges;
    return this;
  }

  public MasterBeanBuilder withExchangeParseDefs(final List<ExchangeParseDef> exchangeParseDefs) {
    this.exchangeParseDefs = exchangeParseDefs;
    return this;
  }

  public MasterBean build() {
    if (exchanges == null || exchangeParseDefs == null) {
      return null;
    }
    this.masterBean = new MasterBeanImpl(this.exchanges, this.exchangeParseDefs);

    return masterBean;
  }
}
