package com.libra.apollo.analytics.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.persistence.Tuple;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.engine.result.AnalyticsResult;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.repository.AnalyticsRepository;
import com.libra.apollo.analytics.repository.InvestmentStyleRepository;
import com.libra.apollo.analytics.repository.LibraStockIndicatorRepository;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.LibraStockIndicatorSpecification;
import com.libra.apollo.analytics.specification.StampDateSpecification;
import com.libra.apollo.analytics.utils.Utils;

@Service
@Transactional
public class AnalyticsServiceImpl implements AnalyticsService {

	@Autowired
	private AnalyticsRepository analyticsRepository;

	@Autowired
	private InvestmentStyleRepository investmentStyleRepository;
	
	@Autowired
	private LibraStockIndicatorRepository libraStockIndicatorRepository;

	@Override
	public List<ApolloAnalytics> getAllApolloAnalytics() {
		return analyticsRepository.findAll();
	}

	@Override
	public List<ApolloAnalytics> getApolloAnalyticsByAnalyticsType(AnalyticsType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScreenerResult getScreeningResults(final AnalyticsType type, final Date runDate, final Collection<Long> stockIds, final Long investmentStyleId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AnalyticsResult getScreeningResults(final AnalyticsType type, final Collection<Long> stockIds, final Long investmentStyleId) {
		
		
		final Specification<LibraStockIndicator> stockIdsSpec = LibraStockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final AnalyticsSpecifications<LibraStockIndicator> specification = new AnalyticsSpecifications<>();
		
		specification.where(stockIdsSpec);
		
		//Obtaining persisted parameters as per investment style
		List<QueryParameter> queryParams = investmentStyleRepository.findQueryParametersById(investmentStyleId);
		
		Consumer<? super QueryParameter> action = consumer -> specification.and(consumer.getSpecification());
		
		queryParams.forEach(action);
		
		//TODO: persist in the database
		List<ValueDataFieldType> fields = Arrays.asList(
				ValueDataFieldType.MAX_STAMP_DATE, 
				ValueDataFieldType.STAR_RATING,
				ValueDataFieldType.FAIR_VALUE,
				ValueDataFieldType.INTRINSIC_VALUE,
				ValueDataFieldType.STOCK_ID
				);
		
		List<ValueDataFieldType> processedParameters = new ArrayList<ValueDataFieldType>();
		
		List<Tuple> returnTuples = libraStockIndicatorRepository.findAllBySpecification(fields, specification);
		
		List<List<Object>> fieldResults = Utils.fromTupleToList(returnTuples, fields);
		
		ScreenerResult results = new ScreenerResult(fields,processedParameters);
		
		if(results.isMergeEnabled()) {
			
			for(List<Object> object : fieldResults) {
			}
		}
		
		return results;
	}
}
