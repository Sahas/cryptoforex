package com.crypto.forex.repositories;

import java.util.List;
import com.crypto.forex.mongo.documents.ExchangeCoinPrice;

public interface FullExchangeCoinPriceDataCustomRepository {

  List<ExchangeCoinPrice> getLatestExchangeCoinPriceDataOfExchanges(List<String> exchangeIds);
}
