package com.crypto.forex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import com.crypto.forex.exchange.api.ExchangeApi;
import com.crypto.forex.mongo.documents.Exchange;
import com.crypto.forex.mongo.documents.Exchanges;
import com.crypto.forex.parse.ExchangeParseDef;
import com.crypto.forex.utils.CryptoForexConsts;

@Configuration
public class CryptoForexConfig {

  @Bean
  public MasterBean masterBean() throws JAXBException {
    List<Exchange> exchanges = null;
    final List<ExchangeParseDef> exchangeParseDefs = new ArrayList<>();

    final JAXBContext context = JAXBContext.newInstance(Exchanges.class);
    final Unmarshaller um = context.createUnmarshaller();
    final File f = new File(CryptoForexConsts.EXCHNAGES_INFO_PATH);
    System.out.println("Unmarshalling exchanges xml...");
    final Exchanges ex = (Exchanges) um.unmarshal(f);
    exchanges = ex.getExchange();

    // final JAXBContext apiDefcontext = JAXBContext.newInstance(ExchangeApiDef.class);
    // final Unmarshaller apiDefum = apiDefcontext.createUnmarshaller();
    // final File apiDefDirectory = new File(CryptoForexConsts.EXCHNAGES_API_DEF_FOLDER);
    // for (final String apiDefFileName : apiDefDirectory.list()) {
    // System.out.println("Unmarshalling xml : " + apiDefFileName);
    // final File file = new File(CryptoForexConsts.EXCHNAGES_API_DEF_FOLDER + apiDefFileName);
    // exchangeParseDefs.add((ExchangeApiDef) apiDefum.unmarshal(file));
    // }
    final MasterBeanBuilder builder = new MasterBeanBuilder();
    return builder.withExchanges(exchanges).withExchangeParseDefs(exchangeParseDefs).build();
  }

  @Bean
  public List<String> availableExchanges() {
    final List<String> availableExchanges = new ArrayList<>();
    final ClassPathScanningCandidateComponentProvider annotationScanner =
        new ClassPathScanningCandidateComponentProvider(true);
    annotationScanner.addIncludeFilter(new AnnotationTypeFilter(ExchangeApi.class));
    final Set<BeanDefinition> annotatedBeans =
        annotationScanner.findCandidateComponents("com.crypto.forex.exchange.api");

    annotatedBeans.forEach(annotatedBean -> {
      try {
        final Class<?> c = Class.forName(annotatedBean.getBeanClassName());
        availableExchanges.add(c.getAnnotation(ExchangeApi.class).exchange());

      } catch (final Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
    return availableExchanges;
  }
}
