package com.crypto.forex.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.forex.entities.Coins;

@RestController
public class CoinDataController {
	
	@RequestMapping(path="/coindata", method= {RequestMethod.GET})
	public List<Coins> fetchCoinData(){
		
	}
}
