package com.crypto.forex.mongo.documents;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;
import com.crypto.forex.utils.CryptoForexConsts;


public class JAXBGenerationTests {
  
  @Test
  public void jaxbShouldProperlyLoadExchangesXml() throws JAXBException{
    final JAXBContext context = JAXBContext.newInstance(Exchanges.class);
    System.out.println("Output from our XML File: ");
    final Unmarshaller um = context.createUnmarshaller();
    final File f = new File(CryptoForexConsts.RESOURCES_HOME + "static/exchanges/ExchangesInfo.xml");
    final Exchanges ex = (Exchanges)um.unmarshal(f);
    assertThat(ex.getExchange().get(0).getName()).isEqualToIgnoringCase("binance");
    assertThat(ex.getExchange().get(0).getTradingCountries().get(0).getCurrency())
        .isEqualToIgnoringCase("USD");
  }
  
  
  @Test
  public void jaxbShouldProperlyOutputExchangesXml() throws JAXBException{
    final ByteArrayOutputStream bs = new ByteArrayOutputStream();
    final Exchanges exchanges = new Exchanges();
    final Exchange binance = new Exchange();
    binance.setId("binance");
    binance.setName("binance");
    binance.addTradingCountry(Countries.USA);
    exchanges.getExchange().add(binance);
    final JAXBContext context = JAXBContext.newInstance(Exchanges.class);
    System.out.println("Output: ");
    final Marshaller ma = context.createMarshaller();
    ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    ma.marshal(exchanges, bs);
    final String xml = bs.toString();
    assertThat(xml).contains("binance");
    assertThat(xml).contains("USA");
    System.out.println(xml);
  }
  
}
