package com.crypto.forex.exchange.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ExchangeApi(exchange = "cryptopia")
public class CryptopiaJson extends AbstractExchangeCoinPriceJson {
	
	@JsonProperty("Label")
	private String label;
	
	@JsonProperty("BaseVolume")
	private double volume;
	
	@JsonProperty("AskPrice")
	private double askPrice;
	
	@JsonProperty("BidPrice")
	private double bidPrice;
	
	@JsonProperty("LastPrice")
	private double lastPrice;
	

	@Override
	public Double getPrice() {
		return this.lastPrice;
	}

	@Override
	public Double getCurrentAskPrice() {
		return this.askPrice;
	}

	@Override
	public Double getCurrentBidPrice() {
		return this.bidPrice;
	}

	@Override
	public String getBaseCoin() {
		return this.getLabel().split("/")[0].toUpperCase();
	}

	@Override
	public String getPeggedCoin() {
		return this.getLabel().split("/")[1].toUpperCase();
	}

	@Override
	public Double getTradedVolume() {
		return this.volume;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}


}
