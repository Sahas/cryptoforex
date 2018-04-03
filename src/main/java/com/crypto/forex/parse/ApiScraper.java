package com.crypto.forex.parse;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.crypto.forex.mongo.documents.CoinPrice;
import com.crypto.forex.utils.CoinData;
import com.fasterxml.jackson.databind.JsonNode;

public class ApiScraper implements Scraper {

  private static Logger logger = LoggerFactory.getLogger(ApiScraper.class);

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
        if (baseCoinSym != null && peggedCoinSym != null
            && CoinData.coinMap.containsKey(baseCoinSym)
            && CoinData.coinMap.containsKey(peggedCoinSym)) {
          coinPrices
              .add(new CoinPrice(exchangeId, baseCoinSym, peggedCoinSym, peggedCoinSym, price,
                  price, price, price));
        } else {
          logger.debug("Skipped Coin price creation for :" + exchangeId + ":"
              + rawCoinPrice.get(apiDef.getBaseCoin().getAttrName()).asText());
        }

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

    return CoinData.getOverrideCoinValue(result);
  }

}
