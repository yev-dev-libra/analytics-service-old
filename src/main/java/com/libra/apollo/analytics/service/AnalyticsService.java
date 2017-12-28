package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.libra.apollo.analytics.engine.result.AnalyticsResult;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;

public interface AnalyticsService {
	
	List<ApolloAnalytics> getAllApolloAnalytics();
	List<ApolloAnalytics> getApolloAnalyticsByAnalyticsType(AnalyticsType type);
	AnalyticsResult getScreeningResults(AnalyticsType type, Collection<Long> instrumentIds, final Long investmentStyleId);
	AnalyticsResult getScreeningResults(AnalyticsType type, Date runDate, Collection<Long> instrumentIds, final Long investmentStyleId);

}
