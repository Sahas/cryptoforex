package com.crypto.forex.exchange.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractExchangeCoinPriceJson implements ExchangeCoinPriceJson {
  private static Logger LOGGER = LoggerFactory.getLogger(AbstractExchangeCoinPriceJson.class);

  @Override
  public String getExchangeId() {
    return this.getClass().getAnnotation(ExchangeApi.class).exchange();
  }
}
