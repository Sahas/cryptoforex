package com.crypto.forex.entities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

import com.crypto.forex.parse.ApiScraper;

public class Binance extends ApiScraper implements Exchange {

	private List<Coins> availableCoins = new ArrayList<>();
	private Map<Coins, Double> coinsWithDrawalFeeMap = new HashMap<>();
	private Map<Coins, Double> coinTradingPrices = new HashMap<>();
	private URI baseApi;
	private URI priceApi;
	
	public Binance() throws URISyntaxException{
		this.baseApi = new URI("https://api.binance.com/");
		this.priceApi = new URI(this.baseApi.toString() + "api/v3/ticker/price");
	}
	
	public List<Coins> getAvailableCoins() {
		return availableCoins;
	}
	
	public void addCoinToExchange(Coins coin){
		this.availableCoins.add(coin);
	}
	
	public void removeCoinFromExchange(Coins coin){
		this.availableCoins.remove(coin);
	}

	public void setAvailableCoins(List<Coins> availableCoins) {
		this.availableCoins = availableCoins;
	}

	public Map<Coins, Double> getCoinsWithDrawalFeeMap() {
		return coinsWithDrawalFeeMap;
	}
	
	public void addCoinsWithdrawalFee(Coins coin, Double price){
		this.coinsWithDrawalFeeMap.put(coin, price);
	}
	
	public void removeCoinsWithdrawalFee(Coins coin){
		this.coinsWithDrawalFeeMap.remove(coin);
	}

	public void setCoinsWithDrawalFeeMap(Map<Coins, Double> coinsWithDrawalFeeMap) {
		this.coinsWithDrawalFeeMap = coinsWithDrawalFeeMap;
	}

	

	public Map<Coins, Double> getCoinTradingPrices() {
		return coinTradingPrices;
	}

	public void setCoinTradingPrices(Map<Coins, Double> coinTradingPrices) {
		this.coinTradingPrices = coinTradingPrices;
	}
	
	public void addCoinTradingPrice(Coins coin, Double price) {
		this.coinTradingPrices.put(coin, price);
	}
	
	public void removeCoinTradingPrice(Coins coin) {
		this.coinTradingPrices.remove(coin);
	}

	@Override
	public List<Coins> availableCoins() {
		return availableCoins;
	}

	@Override
	public Double getTradingPriceOfCoin(Coins coin) {
		return this.coinTradingPrices.get(coin);
	}

	@Override
	public Map<Coins, Double> getTradingPriceOfCoins(List<Coins> coins) {
		Map<Coins,Double> coinMap = new HashMap<>();
		coins.forEach(coin -> coinMap.put(coin, this.getTradingPriceOfCoin(coin)));
		return coinMap;
	}

	@Override
	public Double getWithDrawalFeeOfCoin(Coins coin) {
		return this.coinsWithDrawalFeeMap.get(coin);
	}

	@Override
	public Map<Coins, Double> getWithDrawalFeeOfCoins(List<Coins> coins) {
		Map<Coins,Double> coinMap = new HashMap<>();
		coins.forEach(coin -> coinMap.put(coin, this.getWithDrawalFeeOfCoin(coin)));
		return coinMap;
	}

	@Override
	public boolean isCoinTraded(Coins coin) {
		return availableCoins.contains(coin);
	}

	@Override
	public Currencies currencyType() {
		return Currencies.USD;
	}

	@Override
	public URI getPriceApiForCoin(Coins coin) {
		return UriComponentsBuilder.fromUri(priceApi).queryParam("symbol", coin.getSym()+"UDST").build().toUri();
	}

	@Override
	public String symbolAttrNameInResponse() {
		return "symbol";
	}

	@Override
	public String priceAttrNameInResponse() {
		return "price";
	}

}
