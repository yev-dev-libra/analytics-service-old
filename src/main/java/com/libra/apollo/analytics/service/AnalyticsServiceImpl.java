package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.StockIndicator;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.repository.StockIndicatorRepository;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.StockIndicatorSpecification;
import com.netflix.servo.util.Preconditions;

@Service
@Transactional
public class AnalyticsServiceImpl implements AnalyticsService {

	
	@Autowired
	private StockIndicatorRepository StockIndicatorRepository;


	@Override
	public List<Tuple> getScreeningResults(final Collection<Long> stockIds, final List<QueryParameter> queryParams,  List<ValueDataFieldType> requestedFields, final Date stampDate) {
		Preconditions.checkArgument(stockIds != null, "Passed Stock Ids can not be null");
		Preconditions.checkArgument(queryParams != null, "Passed QueryParameters can not be null");
		Preconditions.checkArgument(requestedFields != null, "Passed ValueDataFieldType can not be null");
		Preconditions.checkArgument(stampDate != null, "Passed runDate can not be null");
		
		final Specification<StockIndicator> stockIdsSpec = StockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final AnalyticsSpecifications<StockIndicator> specification = new AnalyticsSpecifications<>(stockIdsSpec);
		
		
		for(QueryParameter param : queryParams) {
			specification.and(param.getSpecification());
			
		}
		return StockIndicatorRepository.findAllBySpecification(requestedFields, specification, stampDate);
	}

	@Override
	public List<Tuple> getScreeningResults(final Collection<Long> stockIds, final List<QueryParameter> queryParams,  List<ValueDataFieldType> requestedFields) {
		Preconditions.checkArgument(stockIds != null, "Passed Stock Ids can not be null");
		Preconditions.checkArgument(queryParams != null, "Passed QueryParameters can not be null");
		Preconditions.checkArgument(requestedFields != null, "Passed ValueDataFieldType can not be null");
		
		final Specification<StockIndicator> stockIdsSpec = StockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final AnalyticsSpecifications<StockIndicator> specification = new AnalyticsSpecifications<>(stockIdsSpec);
		
		
		for(QueryParameter param : queryParams) {
			specification.and(param.getSpecification());
			
		}
		
		return StockIndicatorRepository.findAllBySpecification(requestedFields, specification);
		
	}
}
