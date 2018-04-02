package com.crypto.forex;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.crypto.forex.mongo.documents.Exchange;
import com.crypto.forex.repositories.ExchangeRepository;

@EnableMongoRepositories(basePackages = {"com.crypto.forex.repositories"})
@Configuration
public class MongoDBConfig {

  @Autowired
  MasterBean masterbean;
  //
  // @Bean
  // public MongoClient mongoClient(@Value("${spring.data.mongodb.host}") final String host,
  // @Value("${spring.data.mongodb.port}") final int port) {
  // return new MongoClient(host, port);
  // }
  //
  // @Bean
  // public MongoTemplate mongoTemplate(@Value("${spring.data.mongodb.host}") final String host,
  // @Value("${spring.data.mongodb.port}") final int port,
  // @Value("${spring.data.mongodb.database}") final String database) {
  // return new MongoTemplate(mongoClient(host, port), database);
  // }


  @Bean
  public CommandLineRunner commandLineRunner(final ExchangeRepository exchangeRepository) {
    return strings ->{
      final List<Exchange> exchanges = masterbean.getAvailableExchanges();
      exchangeRepository.saveAll(exchanges);
    };
  }
}
