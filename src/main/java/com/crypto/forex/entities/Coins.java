package com.crypto.forex.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;

import com.crypto.forex.utils.CryptoForexConsts;

public enum Coins {
	BITCOIN(CryptoForexConsts.BITCOIN, CryptoForexConsts.coinSymMap.get(CryptoForexConsts.BITCOIN), new HashMap<>()),
	ETHEREUM(CryptoForexConsts.ETHEREUM, CryptoForexConsts.coinSymMap.get(CryptoForexConsts.ETHEREUM), new HashMap<>()),
	LITECOIN(CryptoForexConsts.LITECOIN, CryptoForexConsts.coinSymMap.get(CryptoForexConsts.LITECOIN), new HashMap<>()),
	TRON(CryptoForexConsts.TRON, CryptoForexConsts.coinSymMap.get(CryptoForexConsts.TRON), new HashMap<>()),
	NEO(CryptoForexConsts.NEO, CryptoForexConsts.coinSymMap.get(CryptoForexConsts.NEO), new HashMap<>()),
	RIPPLE(CryptoForexConsts.RIPPLE, CryptoForexConsts.coinSymMap.get(CryptoForexConsts.RIPPLE), new HashMap<>()),
	BITCOIN_CASH(CryptoForexConsts.BITCOIN_CASH, CryptoForexConsts.coinSymMap.get(CryptoForexConsts.BITCOIN_CASH), new HashMap<>());
	
	String name;
	String sym;
	Map<Exchanges, Double> exchangePrices;
	
	Coins(String name, String sym, Map<Exchanges, Double> exchangePrices){
		this.name = name;
		this.sym = sym;
		this.exchangePrices = exchangePrices;
	}
	
	public void setExchangePrice(Exchanges exchange, double price){
		this.exchangePrices.put(exchange, price);
	}
	
	public Map<Exchanges, Double> getCoinPrices(){
		return this.exchangePrices;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSym() {
		return sym;
	}

	public void setSym(String sym) {
		this.sym = sym;
	}

	public Map<Exchanges, Double> getExchangePrices() {
		return exchangePrices;
	}

	public void setExchangePrices(Map<Exchanges, Double> exchangePrices) {
		this.exchangePrices = exchangePrices;
	}
	
	public static List<Coins> getAllData(){
		return Arrays.nonNullElementsIn(Coins.values());
	}
}
