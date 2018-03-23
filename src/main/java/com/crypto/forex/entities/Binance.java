package com.crypto.forex.entities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crypto.forex.parse.ApiScraper;

public class Binance extends ApiScraper implements Exchange {

	private List<Coins> availableCoins = new ArrayList<>();
	private Map<Coins, Double> coinsWithDrawalFeeMap = new HashMap<>();
	private Map<Coins, Double> coinTradingPrices = new HashMap<>();
	private final URI baseApi;
	private final URI priceApi;
	
	public Binance() throws URISyntaxException{
		this.baseApi = new URI("https://api.binance.com/");
		this.priceApi = new URI(this.baseApi.toString() + "api/v3/ticker/price");
	}
	
	public List<Coins> getAvailableCoins() {
		return availableCoins;
	}
	
	public void addCoinToExchange(final Coins coin){
		this.availableCoins.add(coin);
	}
	
	public void removeCoinFromExchange(final Coins coin){
		this.availableCoins.remove(coin);
	}

	public void setAvailableCoins(final List<Coins> availableCoins) {
		this.availableCoins = availableCoins;
	}

	public Map<Coins, Double> getCoinsWithDrawalFeeMap() {
		return coinsWithDrawalFeeMap;
	}
	
	public void addCoinsWithdrawalFee(final Coins coin, final Double price){
		this.coinsWithDrawalFeeMap.put(coin, price);
	}
	
	public void removeCoinsWithdrawalFee(final Coins coin){
		this.coinsWithDrawalFeeMap.remove(coin);
	}

	public void setCoinsWithDrawalFeeMap(final Map<Coins, Double> coinsWithDrawalFeeMap) {
		this.coinsWithDrawalFeeMap = coinsWithDrawalFeeMap;
	}

	

	public Map<Coins, Double> getCoinTradingPrices() {
		return coinTradingPrices;
	}

	public void setCoinTradingPrices(final Map<Coins, Double> coinTradingPrices) {
		this.coinTradingPrices = coinTradingPrices;
	}
	
	public void addCoinTradingPrice(final Coins coin, final Double price) {
		this.coinTradingPrices.put(coin, price);
	}
	
	public void removeCoinTradingPrice(final Coins coin) {
		this.coinTradingPrices.remove(coin);
	}

	@Override
	public List<Coins> availableCoins() {
		return availableCoins;
	}

	@Override
	public Double getTradingPriceOfCoin(final Coins coin) {
		return this.coinTradingPrices.get(coin);
	}

	@Override
	public Map<Coins, Double> getTradingPriceOfCoins(final List<Coins> coins) {
		final Map<Coins,Double> coinMap = new HashMap<>();
		coins.forEach(coin -> coinMap.put(coin, this.getTradingPriceOfCoin(coin)));
		return coinMap;
	}

	@Override
	public Double getWithDrawalFeeOfCoin(final Coins coin) {
		return this.coinsWithDrawalFeeMap.get(coin);
	}

	@Override
	public Map<Coins, Double> getWithDrawalFeeOfCoins(final List<Coins> coins) {
		final Map<Coins,Double> coinMap = new HashMap<>();
		coins.forEach(coin -> coinMap.put(coin, this.getWithDrawalFeeOfCoin(coin)));
		return coinMap;
	}

	@Override
	public boolean isCoinTraded(final Coins coin) {
		return availableCoins.contains(coin);
	}

	@Override
	public Currencies currencyType() {
		return Currencies.USD;
	}
}
