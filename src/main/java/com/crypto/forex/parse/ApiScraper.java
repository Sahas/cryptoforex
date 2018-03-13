package com.crypto.forex.parse;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.crypto.forex.entities.CoinPrice;
import com.crypto.forex.entities.Coins;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class ApiScraper implements Scraper {
	
	private RestTemplate rest;
	
	public ApiScraper() {
		this.rest = new RestTemplate();
	}
	
	@Override
	public List<CoinPrice> scrape() {
		List<CoinPrice> coinPrices = new ArrayList<>();
		Coins.getAllData().forEach(coin -> {
			CoinPrice coinPrice = new CoinPrice();
			URI url = this.getPriceApiForCoin(coin);
			ResponseEntity<ObjectNode> response = rest.getForEntity(url, ObjectNode.class);
			ObjectNode body = response.getBody();
			coinPrice.setCoin(body.findValue(this.symbolAttrNameInResponse()).asText());
			coinPrice.setPrice(body.findValue(this.priceAttrNameInResponse()).asDouble());
			coinPrices.add(coinPrice);
		});
		return coinPrices;
	}
	
	public abstract URI getPriceApiForCoin(Coins coin);
	
	public abstract String symbolAttrNameInResponse();
	
	public abstract String priceAttrNameInResponse();

}
