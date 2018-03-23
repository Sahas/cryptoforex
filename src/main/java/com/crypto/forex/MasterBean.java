package com.crypto.forex;

import java.util.List;
import com.crypto.forex.mongo.documents.Exchange;
import com.crypto.forex.parse.ExchangeParseDef;

public interface MasterBean {

  List<Exchange> getAvailableExchanges();

  List<String> getAvailableExchangeIds();

  Exchange getExchange(String exchangeId);

  boolean containsExchange(String exchangeId);

  List<ExchangeParseDef> getAllparseDefsForAvailableExchanges();

  ExchangeParseDef getparseDefForExchange(Exchange exchange);

  ExchangeParseDef getparseDefForExchange(String exchangeId);


}
