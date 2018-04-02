package com.crypto.forex.repositories;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.crypto.forex.mongo.documents.FullExchangeCoinPriceData;


public interface FullExchangeCoinPriceDataRepository
    extends MongoRepository<FullExchangeCoinPriceData, ObjectId>,
    FullExchangeCoinPriceDataCustomRepository {


  @Query(value = "{'exchangescoinprices.coinprices.exchange._id': {$in: ?0}}")
  Page<FullExchangeCoinPriceData> findCoinPricesForExchanges(List<String> exchangeIds,
      Pageable pageable);
}
