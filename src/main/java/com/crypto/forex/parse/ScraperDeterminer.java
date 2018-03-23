package com.crypto.forex.parse;

public class ScraperDeterminer {
  public static Scraper getScraperFor(final ExchangeParseDef parseDef) {
    if (parseDef.getType().equalsIgnoreCase("api")) {
      return new ApiScraper();
    }
    return null;
  }
}
