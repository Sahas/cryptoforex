package com.crypto.forex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crypto.forex.mongo.documents.Exchange;
import com.crypto.forex.parse.ExchangeParseDef;

public class MasterBeanImpl implements MasterBean {

  List<Exchange> exchanges = new ArrayList<>();
  List<ExchangeParseDef> exchangeParseDefs = new ArrayList<>();
  Map<String, ExchangeParseDef> exchangeParseDefMap = new HashMap<>();
  Map<String, Exchange> exchangeMap = new HashMap<>();

  MasterBeanImpl(final List<Exchange> exchanges, final List<ExchangeParseDef> exchangeParseDefs) {
    this.exchanges = exchanges;
    this.exchangeParseDefs = exchangeParseDefs;
    this.exchangeMap = constructExchangeMap(exchanges);
    this.exchangeParseDefMap = constructParseDefMap(exchangeParseDefs);
  }

  private Map<String, Exchange> constructExchangeMap(final List<Exchange> exchanges) {
    final Map<String, Exchange> exchangeMap = new HashMap<>();
    exchanges.forEach(exchange -> {
      exchangeMap.put(exchange.getId(), exchange);
    });
    return exchangeMap;
  }

  private Map<String, ExchangeParseDef> constructParseDefMap(
      final List<ExchangeParseDef> exchangeParseDefs) {
    final Map<String, ExchangeParseDef> exchangeParseDefMap = new HashMap<>();
    exchangeParseDefs.forEach(exchangeParseDef -> {
      exchangeParseDefMap.put(exchangeParseDef.getExchange(), exchangeParseDef);
    });
    return exchangeParseDefMap;
  }

  @Override
  public List<Exchange> getAvailableExchanges() {
    return exchanges;
  }

  @Override
  public Exchange getExchange(final String exchangeId) {
    return this.exchangeMap.get(exchangeId);
  }


  @Override
  public List<String> getAvailableExchangeIds() {
    return new ArrayList<>(this.exchangeMap.keySet());
  }

  @Override
  public boolean containsExchange(final String exchangeId) {
    return this.exchangeMap.containsKey(exchangeId);
  }

  @Override
  public List<ExchangeParseDef> getAllparseDefsForAvailableExchanges() {
    return this.exchangeParseDefs;
  }

  @Override
  public ExchangeParseDef getparseDefForExchange(final Exchange exchange) {
    return getparseDefForExchange(exchange.getId());
  }

  @Override
  public ExchangeParseDef getparseDefForExchange(final String exchangeId) {
    return this.exchangeParseDefMap.get(exchangeId);
  }

}
