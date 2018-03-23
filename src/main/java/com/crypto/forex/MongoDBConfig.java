package com.crypto.forex;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.crypto.forex.mongo.documents.Exchange;
import com.crypto.forex.repositories.ExchangeRepository;

@EnableMongoRepositories(basePackages={"com.crypto.forex.repositories"})
@Configuration
public class MongoDBConfig {

  @Autowired
  MasterBean masterbean;

  @Bean
  public CommandLineRunner commandLineRunner(final ExchangeRepository exchangeRepository) {
    return strings ->{
      final List<Exchange> exchanges = masterbean.getAvailableExchanges();
      exchangeRepository.saveAll(exchanges);
    };
  }
}
