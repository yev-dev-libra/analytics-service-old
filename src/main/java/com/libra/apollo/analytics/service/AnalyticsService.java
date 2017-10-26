package com.libra.apollo.analytics.service;

import java.util.List;

import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.ApolloAnalytics;

public interface AnalyticsService {
	
	List<ApolloAnalytics> getAllApolloAnalytics();
	
	List<ApolloAnalytics> getApolloAnalyticsByAnalyticsType(AnalyticsType type);

}
