package com.crypto.forex.repositories;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.ArrayOperators.Filter.filter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import com.crypto.forex.mongo.documents.ExchangeCoinPrice;
import com.crypto.forex.mongo.documents.FullExchangeCoinPriceData;

@Component
public class FullExchangeCoinPriceDataCustomRepositoryImpl
    implements FullExchangeCoinPriceDataCustomRepository {

  @Autowired
  MongoTemplate mongoTemplate;

  @Override
  public List<ExchangeCoinPrice> getLatestExchangeCoinPriceDataOfExchanges(
      final List<String> exchangeIds) {
    final SortOperation sortByDateDescending = sort(Sort.Direction.DESC, "createdtime");
    final GroupOperation getLatestExchangeCoinPrices = group("_id").first("exchangescoinprices").as("exchangescoinprices");
    final ProjectionOperation filterExchangeCoinPricesByIds = project().and(filter("exchangescoinprices").as("exchangecoinprice").by(Criteria.where("exchangecoinprice.exchange._id").in(exchangeIds).getCriteriaObject())).as("exchangecoinprices");
    final TypedAggregation<FullExchangeCoinPriceData> aggregation =
        newAggregation(FullExchangeCoinPriceData.class, sortByDateDescending,
            getLatestExchangeCoinPrices, filterExchangeCoinPricesByIds);
    final AggregationResults<ExchangeCoinPrice> result =
        mongoTemplate.aggregate(aggregation, ExchangeCoinPrice.class);
    return result.getMappedResults();
  }

}
