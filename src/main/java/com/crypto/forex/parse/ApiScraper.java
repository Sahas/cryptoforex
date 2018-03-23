package com.crypto.forex.parse;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.crypto.forex.mongo.documents.CoinPrice;
import com.fasterxml.jackson.databind.JsonNode;

public class ApiScraper implements Scraper {

  private final RestTemplate rest;

  public ApiScraper() {
    this.rest = new RestTemplate();
  }

  @Override
  public List<CoinPrice> scrape(final ExchangeParseDef parseDef) {
    final ExchangeApiDef apiDef = (ExchangeApiDef) parseDef;
    final String exchangeId = apiDef.getExchange();
    final List<CoinPrice> coinPrices = new ArrayList<>();
    final UriComponents uri =
        UriComponentsBuilder.fromHttpUrl(apiDef.getBaseUri()).path(apiDef.getPriceUri()).build();
    final ResponseEntity<JsonNode> response = rest.getForEntity(uri.toUriString(), JsonNode.class);
    final JsonNode body = response.getBody();
    if (body.isArray()) {
      body.elements().forEachRemaining(rawCoinPrice -> {
        final Double price = rawCoinPrice.get(apiDef.getPrice().getAttrName()).asDouble();
        final String baseCoinSym =
            fetchCoinFrom(rawCoinPrice.get(apiDef.getBaseCoin().getAttrName()).asText(),
                apiDef.getBaseCoin().getOperation());
        final String peggedCoinSym =
            fetchCoinFrom(rawCoinPrice.get(apiDef.getPeggedCoin().getAttrName()).asText(),
                apiDef.getPeggedCoin().getOperation());
        coinPrices.add(new CoinPrice(exchangeId, baseCoinSym, peggedCoinSym, price));
      });
    }
    return coinPrices;
  }

  public String fetchCoinFrom(final String rawString,
      final List<Operation> operations) {
    if (operations == null || operations.isEmpty()) {
      return rawString;
    }
    String result = rawString;
    for (final Operation operation : operations) {
      result = StringOperations.perform(result, operation);
    }
    return result;
  }

}
