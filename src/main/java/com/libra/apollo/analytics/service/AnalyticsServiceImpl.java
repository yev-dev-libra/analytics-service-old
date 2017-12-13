package com.libra.apollo.analytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.repository.AnalyticsRepository;

@Service
@Transactional
public class AnalyticsServiceImpl implements AnalyticsService {

	@Autowired
	private AnalyticsRepository analyticsRepository;

	
	
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
	public ScreenerResult getAnalyticsResult(ScreenerRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
