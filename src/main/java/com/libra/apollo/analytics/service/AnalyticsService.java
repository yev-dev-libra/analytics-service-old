package com.libra.apollo.analytics.service;

import java.util.List;

import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;

public interface AnalyticsService {
	
	List<ApolloAnalytics> getAllApolloAnalytics();
	
	List<ApolloAnalytics> getApolloAnalyticsByAnalyticsType(AnalyticsType type);
	
	ScreenerResult getAnalyticsResult(ScreenerRequest request);

}
