package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		final Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateGreatestGroupedByStockId();
		
		final Specification<LibraStockIndicator> groupByStockIdSpec = LibraStockIndicatorSpecification.groupByStockId();
		
		final Specification<LibraStockIndicator> stockIdsSpec = LibraStockIndicatorSpecification.stockIdsEquals(stockIds);
		
		final AnalyticsSpecifications<LibraStockIndicator> specs = new AnalyticsSpecifications<>(stockIdsSpec);
		
		specs.and(groupByStockIdSpec);
		
		Iterable<QueryParameter> queryParams = investmentStyleRepository.findIterableQueryParametersById(investmentStyleId);
		
		Consumer<? super QueryParameter> action = consumer -> specs.and(consumer.getSpecification());
		
		queryParams.forEach(action);
		
		
		//TODO: add sorting
		List<LibraStockIndicator> indicators = libraStockIndicatorRepository.findAll(specs); 
		
		ScreenerResult results = new ScreenerResult(indicators);
		
		return results;
	}
}
