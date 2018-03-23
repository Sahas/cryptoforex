package com.crypto.forex.mongo.documents;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;
import com.crypto.forex.parse.ExchangeApiDef;
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
    assertThat(ex.getExchange().get(1).getName()).isEqualToIgnoringCase("coindelta");
    assertThat(ex.getExchange().get(1).getTradingCountries().get(0).getCurrency())
        .isEqualToIgnoringCase("INR");
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
  
  // Make sure package-info exists
  // Class not found exception => Check build path or maven compile
  // All inner elements null => package-info - elementFormDefault = XmlNsForm.QUALIFIED OR add
  // namespace to all elements
  // Make all methods in object factory static

  @Test
  public void jaxbShouldProperlyLoadBinanceExchangeDefXml() throws JAXBException {
    final JAXBContext context = JAXBContext.newInstance(ExchangeApiDef.class);
    System.out.println("Output from our XML File: ");
    final Unmarshaller um = context.createUnmarshaller();
    final File f =
        new File(CryptoForexConsts.EXCHNAGES_API_DEF_FOLDER + "binance_api_strategy.xml");
    final ExchangeApiDef exDef = (ExchangeApiDef) um.unmarshal(f);
    assertThat(exDef.getBaseUri()).isEqualToIgnoringCase("https://api.binance.com");
    assertThat(exDef.getPriceUri()).isEqualToIgnoringCase("api/v3/ticker/bookTicker");
    assertThat(exDef.getPrice().getAttrName()).isEqualToIgnoringCase("bidPrice");
    assertThat(exDef.getBaseCoin().getAttrName()).isEqualToIgnoringCase("symbol");
    assertThat(exDef.getPeggedCoin().getAttrName()).isEqualToIgnoringCase("symbol");
    assertThat(exDef.getPeggedCoin().getOperation().get(0).getName())
        .isEqualToIgnoringCase("determine");
    assertThat(exDef.getBaseCoin().getOperation().get(0).getName())
        .isEqualToIgnoringCase("determine");
    assertThat(exDef.getBaseCoin().getOperation().get(0).getArgs().get(0).toString())
        .isEqualToIgnoringCase("0");
    assertThat(exDef.getPeggedCoin().getOperation().get(0).getArgs().get(0).toString())
        .isEqualToIgnoringCase("1");
  }

  @Test
  public void jaxbShouldProperlyLoadCoinDeltaExchangeDefXml() throws JAXBException {
    final JAXBContext context = JAXBContext.newInstance(ExchangeApiDef.class);
    System.out.println("Output from our XML File: ");
    final Unmarshaller um = context.createUnmarshaller();
    final File f =
        new File(CryptoForexConsts.EXCHNAGES_API_DEF_FOLDER + "coindelta_api_strategy.xml");
    final ExchangeApiDef exDef = (ExchangeApiDef) um.unmarshal(f);
    assertThat(exDef.getBaseUri()).isEqualToIgnoringCase("https://coindelta.com");
    assertThat(exDef.getPriceUri()).isEqualToIgnoringCase("api/v1/public/getticker");
    assertThat(exDef.getPrice().getAttrName()).isEqualToIgnoringCase("Last");
    assertThat(exDef.getBaseCoin().getAttrName()).isEqualToIgnoringCase("MarketName");
    assertThat(exDef.getPeggedCoin().getAttrName()).isEqualToIgnoringCase("MarketName");
    assertThat(exDef.getPeggedCoin().getOperation().get(0).getName())
        .isEqualToIgnoringCase("split");
    assertThat(exDef.getBaseCoin().getOperation().get(0).getName()).isEqualToIgnoringCase("split");
    assertThat(exDef.getBaseCoin().getOperation().get(0).getArgs().get(0).toString())
        .isEqualToIgnoringCase("-");
    assertThat(exDef.getPeggedCoin().getOperation().get(0).getArgs().get(0).toString())
        .isEqualToIgnoringCase("-");
    assertThat(exDef.getBaseCoin().getOperation().get(0).getArgs().get(1).toString())
        .isEqualToIgnoringCase("0");
    assertThat(exDef.getPeggedCoin().getOperation().get(0).getArgs().get(1).toString())
        .isEqualToIgnoringCase("1");
  }

}
