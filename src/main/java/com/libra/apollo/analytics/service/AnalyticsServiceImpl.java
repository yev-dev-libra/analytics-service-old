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
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.repository.LibraStockIndicatorRepository;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.LibraStockIndicatorSpecification;
import com.netflix.servo.util.Preconditions;

@Service
@Transactional
public class AnalyticsServiceImpl implements AnalyticsService {

	
	@Autowired
	private LibraStockIndicatorRepository libraStockIndicatorRepository;


	@Override
	public List<Tuple> getScreeningResults(final Collection<Long> stockIds, final List<QueryParameter> queryParams,  List<ValueDataFieldType> requestedFields, final Date runDate) {
		Preconditions.checkArgument(stockIds != null, "Passed Stock Ids can not be null");
		Preconditions.checkArgument(queryParams != null, "Passed QueryParameters can not be null");
		Preconditions.checkArgument(requestedFields != null, "Passed ValueDataFieldType can not be null");
		Preconditions.checkArgument(runDate != null, "Passed runDate can not be null");
		
		final Specification<LibraStockIndicator> stockIdsSpec = LibraStockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final AnalyticsSpecifications<LibraStockIndicator> specification = new AnalyticsSpecifications<>(stockIdsSpec);
		
		
		for(QueryParameter param : queryParams) {
			specification.and(param.getSpecification());
			
		}
		return libraStockIndicatorRepository.findAllBySpecification(requestedFields, specification, runDate);
	}

	@Override
	public List<Tuple> getScreeningResults(final Collection<Long> stockIds, final List<QueryParameter> queryParams,  List<ValueDataFieldType> requestedFields) {
		Preconditions.checkArgument(stockIds != null, "Passed Stock Ids can not be null");
		Preconditions.checkArgument(queryParams != null, "Passed QueryParameters can not be null");
		Preconditions.checkArgument(requestedFields != null, "Passed ValueDataFieldType can not be null");
		
		final Specification<LibraStockIndicator> stockIdsSpec = LibraStockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final AnalyticsSpecifications<LibraStockIndicator> specification = new AnalyticsSpecifications<>(stockIdsSpec);
		
		
		for(QueryParameter param : queryParams) {
			specification.and(param.getSpecification());
			
		}
		
		return libraStockIndicatorRepository.findAllBySpecification(requestedFields, specification);
		
	}
}
