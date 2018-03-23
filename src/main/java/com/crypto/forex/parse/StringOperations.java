package com.crypto.forex.parse;

import java.util.List;
import com.crypto.forex.utils.CoinData;

public class StringOperations {

  public static final String SUBSTRING = "subString";
  public static final String SPLIT = "split";
  public static final String DETERMINE = "determine";

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

    }
    return null;
  }

  public static String determineCoin(final String rawString, final int position) {
    String coin = null;
    for (int i = 2; i < rawString.length() - 1; i++) {
      final String baseString = rawString.substring(0, i);
      final String peggedString = rawString.substring(i);
      if (CoinData.coinMap.containsKey(baseString) && CoinData.coinMap.containsKey(peggedString)) {
        coin = (position == 0) ? baseString : peggedString;
        break;
      }
    }
    return coin;
  }
}
