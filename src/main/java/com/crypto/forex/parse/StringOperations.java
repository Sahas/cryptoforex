package com.crypto.forex.parse;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.crypto.forex.utils.CoinData;

public class StringOperations {

  private static Logger LOGGER = LoggerFactory.getLogger(StringOperations.class);
  public static final String SUBSTRING = "subString";
  public static final String SPLIT = "split";
  public static final String DETERMINE = "determine";
  public static final String TO_UPPER_CASE = "upperCase";
  public static final String TO_STRING = "toString";

  public static String perform(final String rawString,
      final Operation operation) {
    if (operation == null || rawString == null) {
      return rawString;
    }

    final List<String> args = operation.getArgs();
    switch (operation.getName()) {
      case SUBSTRING:
        return rawString.substring(Integer.valueOf(args.get(0)),
            Integer.valueOf(args.get(1)));
      case DETERMINE:
        return determineCoin(rawString, Integer.valueOf(args.get(0)));
      case SPLIT:
        return rawString.split(args.get(0))[Integer.valueOf(args.get(1))];
      case TO_UPPER_CASE:
        return rawString.toUpperCase();
      case TO_STRING:
        return rawString;
    }
    return null;
  }

  public static String determineCoin(String rawString, final int position) {
    String coin = null;
    rawString = rawString.toUpperCase();
    for (int i = 2; i < rawString.length() - 1; i++) {
      final String baseString = rawString.substring(0, i);
      final String peggedString = rawString.substring(i);
      if (CoinData.coinMap.containsKey(baseString) && CoinData.coinMap.containsKey(peggedString)) {
        coin = (position == 0) ? baseString : peggedString;
        break;
      }
    }

    if (coin == null) {
      LOGGER.debug("A Coin could not be determined : " + rawString);
    }
    return coin;
  }
}
