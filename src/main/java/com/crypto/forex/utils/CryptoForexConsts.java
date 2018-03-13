package com.crypto.forex.utils;

import java.util.HashMap;
import java.util.Map;

public final class CryptoForexConsts {
	
	public static final String USD = "USD";
	public static final String INR = "INR";
	public static final String BINANCE = "Binance";
	public static final String COINBASE = "CoinBase";
	public static final String KOINEX = "Koinex";
	public static final String COINDELTA = "CoinDelta";
	public static final String BITBNS = "Bitbns";
	public static final String ZEBPAY = "Zebpay";
	public static final String BITCOIN = "Bitcoin";
	public static final String ETHEREUM = "Ethereum";
	public static final String LITECOIN = "LiteCoin";
	public static final String TRON = "Tron";
	public static final String NEO = "Neo";
	public static final String RIPPLE = "Ripple";
	public static final String BITCOIN_CASH = "BitcoinCash";
	public static final Map<String,String> coinSymMap = new HashMap<>();
	static {
		coinSymMap.put(BITCOIN, "BTC");
		coinSymMap.put(ETHEREUM, "ETH");
		coinSymMap.put(LITECOIN, "LTC");
		coinSymMap.put(TRON, "TRX");
		coinSymMap.put(NEO, "NEO");
		coinSymMap.put(RIPPLE, "XRP");
		coinSymMap.put(BITCOIN_CASH, "BCC");
	}
	
}
