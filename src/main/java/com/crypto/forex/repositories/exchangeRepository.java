package com.crypto.forex.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.crypto.forex.mongo.documents.Exchange;

public interface exchangeRepository extends MongoRepository<Exchange, String>{

}
