package com.crypto.forex.parse;

import java.util.List;
import com.crypto.forex.mongo.documents.CoinPrice;;

@FunctionalInterface
public interface Scraper {
	
  List<CoinPrice> scrape(ExchangeParseDef parseDef);
}
