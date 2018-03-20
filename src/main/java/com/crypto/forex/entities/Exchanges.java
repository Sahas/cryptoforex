package com.crypto.forex.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crypto.forex.utils.CryptoForexConsts;

public enum Exchanges {
	COINBASE(CryptoForexConsts.COINBASE, Currencies.USD, new ArrayList<>(), new HashMap<>()), 
	BINANCE(CryptoForexConsts.BINANCE, Currencies.USD, new ArrayList<>(), new HashMap<>()), 
	KOINEX(CryptoForexConsts.KOINEX, Currencies.RUPEE, new ArrayList<>(), new HashMap<>()), 
	COINDELTA(CryptoForexConsts.COINDELTA, Currencies.RUPEE, new ArrayList<>(), new HashMap<>()), 
	BITBNS(CryptoForexConsts.BITBNS, Currencies.RUPEE, new ArrayList<>(), new HashMap<>()), 
	ZEBPAY(CryptoForexConsts.ZEBPAY, Currencies.RUPEE, new ArrayList<>(), new HashMap<>());
	
	String name;
	Currencies country;
	List<Coins> availableCoins;
	Map<Coins, Double> coinWithdrawalFeeMap;
	
	Exchanges(String name, Currencies country, List<Coins> availableCoins, Map<Coins, Double> coinWithdrawalFeeMap){
		this.name = name;
		this.country = country;
		this.availableCoins = availableCoins;
		this.coinWithdrawalFeeMap = coinWithdrawalFeeMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Currencies getCountry() {
		return country;
	}

	public void setCountry(Currencies country) {
		this.country = country;
	}

	public List<Coins> getAvailableCoins() {
		return availableCoins;
	}

	public void setAvailableCoins(List<Coins> availableCoins) {
		this.availableCoins = availableCoins;
	}

	public Map<Coins, Double> getCoinWithdrawalFeeMap() {
		return coinWithdrawalFeeMap;
	}

	public void setCoinWithdrawalFeeMap(Map<Coins, Double> coinWithdrawalFeeMap) {
		this.coinWithdrawalFeeMap = coinWithdrawalFeeMap;
	}
	
	public void addCoinToExchange(Coins coin){
		this.availableCoins.add(coin);
	}
	
	public void addCoinWithdrawalFee(Coins coin, Double fee){
		this.coinWithdrawalFeeMap.put(coin, fee);
	}
	
	public boolean isCoinTraded(Coins coin){
		return this.availableCoins.contains(coin);
	}
	
}
