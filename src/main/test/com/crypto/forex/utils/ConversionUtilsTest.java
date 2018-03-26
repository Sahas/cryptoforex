package com.crypto.forex.utils;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class ConversionUtilsTest {
  ConversionUtils utils = new ConversionUtils();

  @Test
  public void getExchangePriceForUSDToINRShouldReturnProperExchangeValue() {
    final Double exchangePrice = utils.getExchangePriceFor("USD", "INR");
    System.out.println(exchangePrice);
    assertThat(exchangePrice).isGreaterThan(60.0);
  }

  @Test
  public void getExchangePriceForINRToINRShouldReturnOne() {
    final Double exchangePrice = utils.getExchangePriceFor("INR", "INR");
    System.out.println(exchangePrice);
    assertThat(exchangePrice).isEqualTo(1.0);
  }
}
