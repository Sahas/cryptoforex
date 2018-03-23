package com.crypto.forex.repositories;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.crypto.forex.mongo.documents.ExchangeCoinPrice;
import com.crypto.forex.mongo.documents.FullExchangeCoinPriceData;


public interface FullExchangeCoinPriceDataRepository extends MongoRepository<FullExchangeCoinPriceData, ObjectId> {

  FullExchangeCoinPriceData findFirstByOrderByCreateDateDesc();

  @Query("select data.exchangescoinprices from FullExchangeCoinPriceData data order by createddate LIMIT 1")
  List<ExchangeCoinPrice> getLatestDataForExchanges();

}
