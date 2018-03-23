package com.crypto.forex.parse;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;
import com.crypto.forex.mongo.documents.CoinPrice;
import com.crypto.forex.utils.CryptoForexConsts;

public class ApiScraperTest {

  @Test
  public void fetchCoinFromMethodShoudReturnBTCWhenBTCUSDAndDETERMINEIsPassed() {
    final ApiScraper apiScraper = new ApiScraper();
    final List<Operation> operations = new ArrayList<>();
    final Operation operation = new Operation();
    operation.setName("determine");
    operation.getArgs().add("0");
    operations.add(operation);
    assertThat(apiScraper.fetchCoinFrom("BTCINR", operations)).isEqualTo("BTC");
    operation.getArgs().remove(0);
    operation.getArgs().add("1");
    assertThat(apiScraper.fetchCoinFrom("BTCINR", operations)).isEqualTo("INR");
  }

  @Test
  public void scrapeShouldReturnCoinPricesIfBinanceApiDefIsPassed() throws JAXBException {
    final JAXBContext context = JAXBContext.newInstance(ExchangeApiDef.class);
    System.out.println("Output from our XML File: ");
    final Unmarshaller um = context.createUnmarshaller();
    final File f =
        new File(CryptoForexConsts.RESOURCES_HOME + "static/strategies/binance_api_strategy.xml");
    final ExchangeApiDef exApiDef = (ExchangeApiDef) um.unmarshal(f);
    final ApiScraper apiScraper = new ApiScraper();
    final List<CoinPrice> coinPrices = apiScraper.scrape(exApiDef);
    assertThat(coinPrices).isNotEmpty();
    assertThat(coinPrices.get(0).getExchange().getId()).isEqualToIgnoringCase("binance");
    assertThat(coinPrices.get(0).getBasecoin().getSym()).isNotBlank();

  }
}
