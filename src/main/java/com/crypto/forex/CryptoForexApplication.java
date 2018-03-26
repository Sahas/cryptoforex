package com.crypto.forex;

import javax.xml.bind.JAXBException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAutoConfiguration
public class CryptoForexApplication {

	public static void main(final String[] args) throws JAXBException {
		SpringApplication.run(CryptoForexApplication.class, args);
		
	}
}
