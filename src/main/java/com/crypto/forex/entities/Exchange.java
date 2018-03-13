package com.crypto.forex.entities;

import java.util.List;
import java.util.Map;

import com.crypto.forex.parse.Scraper;

public interface Exchange extends Scraper{
	List<Coins> availableCoins();
	
	boolean isCoinTraded(Coins coin);
	
	Currencies currencyType();
	
	Double getTradingPriceOfCoin(Coins coin);
	
	Map<Coins, Double> getTradingPriceOfCoins(List<Coins> coins);
	
	Double getWithDrawalFeeOfCoin(Coins coin);
	
	Map<Coins, Double> getWithDrawalFeeOfCoins(List<Coins> coin);
	
	
}
