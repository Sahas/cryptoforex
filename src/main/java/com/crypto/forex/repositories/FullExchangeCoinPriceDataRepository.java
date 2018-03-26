package com.crypto.forex.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.crypto.forex.mongo.documents.FullExchangeCoinPriceData;


public interface FullExchangeCoinPriceDataRepository extends MongoRepository<FullExchangeCoinPriceData, ObjectId> {

}
