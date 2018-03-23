package com.crypto.forex.parse;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class StringOperationsTest {

  @Test
  public void determineCoinShouldReturnBTCAndINRWhenBTCINRIsPassed() {
    final String baseCoin = StringOperations.determineCoin("BTCINR", 0);
    final String peggedCoin = StringOperations.determineCoin("BTCINR", 1);
    
    assertThat(baseCoin).isEqualTo("BTC");
    assertThat(peggedCoin).isEqualTo("INR");
  }

  @Test
  public void determineCoinShouldReturnNullWhenRandomStringIsPassed() {
    final String baseCoin = StringOperations.determineCoin("ABCDEF", 0);
    final String peggedCoin = StringOperations.determineCoin("ABCDEF", 1);

    assertThat(baseCoin).isNull();
    assertThat(peggedCoin).isNull();
  }


  @Test
  public void determineCoinShouldReturnCoinStringWhenValidCombinationIsPassed() {
    assertThat(StringOperations.determineCoin("BTCUSD", 0)).isEqualTo("BTC");
    assertThat(StringOperations.determineCoin("BTCUSD", 1)).isEqualTo("USD");
    assertThat(StringOperations.determineCoin("BTCUSDT", 0)).isEqualTo("BTC");
    assertThat(StringOperations.determineCoin("BTCUSDT", 1)).isEqualTo("USDT");
    assertThat(StringOperations.determineCoin("BTCETH", 0)).isEqualTo("BTC");
    assertThat(StringOperations.determineCoin("BTCETH", 1)).isEqualTo("ETH");
  }

}
