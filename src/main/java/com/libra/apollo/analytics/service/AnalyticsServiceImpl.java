package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.engine.converter.AnalyticsConveterConveter;
import com.libra.apollo.analytics.engine.converter.Converter;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.repository.AnalyticsRepository;
import com.libra.apollo.analytics.repository.LibraStockIndicatorRepository;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.LibraStockIndicatorSpecification;

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
	public void getScreeningResults(final Collection<Long> stockIds, final List<QueryParameter> queryParams, final ScreenerResult result, final Date runDate) {
		
		final Specification<LibraStockIndicator> stockIdsSpec = LibraStockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final AnalyticsSpecifications<LibraStockIndicator> specification = new AnalyticsSpecifications<>(stockIdsSpec);
		
		final List<ValueDataFieldType> requestedFields = result.getRequestedFields();
		
		for(QueryParameter param : queryParams) {
			specification.and(param.getSpecification());
			
		}
		
		List<Tuple> from = null;
		
		if(runDate != null) {
			from = libraStockIndicatorRepository.findAllBySpecification(requestedFields, specification, runDate);
		}
		else {
			from = libraStockIndicatorRepository.findAllBySpecification(requestedFields, specification);
		}
		
		Converter<List<Tuple>, Collection<List<?>>> convertedValues = AnalyticsConveterConveter.fromTupleToList(requestedFields);
		
		Collection<List<?>> fieldResults =  convertedValues.convert(from);
		result.merge(fieldResults);
		
	}

	@Override
	public void getScreeningResults(final Collection<Long> stockIds, final List<QueryParameter> queryParams, final ScreenerResult result) {
		getScreeningResults(stockIds, queryParams, result);
	}
}
