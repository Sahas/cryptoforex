package com.crypto.forex;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages={"com.crypto.forex.repositories"})
@Configuration
public class MongoDBConfig {

}
