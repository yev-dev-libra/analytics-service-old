package com.libra.apollo.analytics.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

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
import com.libra.apollo.analytics.utils.Utils;

@Service
@Transactional
public class AnalyticsServiceImpl implements AnalyticsService {

	@Autowired
	private AnalyticsRepository analyticsRepository;

	
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
	public void getScreeningResults(final AnalyticsType type, final Collection<Long> stockIds, final List<QueryParameter> queryParams, final ScreenerResult result, final Date runDate) {
		
		final Specification<LibraStockIndicator> stockIdsSpec = LibraStockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final AnalyticsSpecifications<LibraStockIndicator> specification = new AnalyticsSpecifications<>(stockIdsSpec);
		
		final List<ValueDataFieldType> requestedFields = result.getRequestedFields();
		
		final List<ValueDataFieldType> fields = result.getParameters();
		
		for(QueryParameter param : queryParams) {
			specification.and(param.getSpecification());
			
		}
		
		
		List<Tuple> returnTuples = null;
		
		if(runDate != null) {
			returnTuples = libraStockIndicatorRepository.findAllBySpecification(fields, specification, runDate);
		}
		else {
			returnTuples = libraStockIndicatorRepository.findAllBySpecification(fields, specification);
		}
		
		Iterable<List<Object>> fieldResults = Utils.fromTupleToList(returnTuples, requestedFields);
		
		
		result.merge(fieldResults);
		
	}

	@Override
	public void getScreeningResults(final AnalyticsType type, final Collection<Long> stockIds, final List<QueryParameter> queryParams, final ScreenerResult result) {
		getScreeningResults(type, stockIds, queryParams, result);
	}
}
