package com.crypto.forex.parse;

import java.util.List;

import com.crypto.forex.entities.CoinPrice;

@FunctionalInterface
public interface Scraper {
	
	List<CoinPrice> scrape();
}
