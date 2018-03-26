package com.crypto.forex.utils;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class ConversionUtils {
  private static Logger logger = LoggerFactory.getLogger(ConversionUtils.class);

  private static Map<String, String> currencyOverridingMap = getCurrencyOverridingMap();


  @Cacheable(value = "exchangePrices")
  public Double getExchangePriceFor(String from, final String to) {
    if (from.equalsIgnoreCase(to)) {
      return 1.0;
    }
    from = currencyOverridingMap.getOrDefault(from, from);
    final String exchangeQueryParam = from + "_" + to;
    logger.debug("Getting exchange prices for " + exchangeQueryParam);
    final RestTemplate restTemplate = new RestTemplate();
    final UriComponents uri =
        UriComponentsBuilder.fromHttpUrl("https://free.currencyconverterapi.com/api/v5/convert").queryParam("q", exchangeQueryParam).queryParam("compact", "y").build();
    final ResponseEntity<JsonNode> response =
        restTemplate.getForEntity(uri.toUriString(), JsonNode.class);

    return response.getBody().get(exchangeQueryParam).get("val").asDouble();
  }

  @CacheEvict(allEntries = true, cacheNames = {"exchangePrices"})
  @Scheduled(cron = "0 0 * * * *")
  public void cacheEvict() {};

  private static Map<String, String> getCurrencyOverridingMap() {
    final Map<String, String> map = new HashMap<>();
    map.put("USDT", "USD");
    return map;
  }
}
