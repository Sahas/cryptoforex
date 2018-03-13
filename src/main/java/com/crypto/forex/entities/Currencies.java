package com.crypto.forex.entities;

import com.crypto.forex.utils.CryptoForexConsts;

public enum Currencies {
	RUPEE(CryptoForexConsts.INR), USD(CryptoForexConsts.USD);
	
	String name;
	
	Currencies(String name){
		this.name = name;
	}
}
