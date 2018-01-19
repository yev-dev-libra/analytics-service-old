package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;

public interface AnalyticsService {
	
	public List<ApolloAnalytics> getAllApolloAnalytics();
	public List<ApolloAnalytics> getApolloAnalyticsByAnalyticsType(final AnalyticsType type);
	public void getScreeningResults(final Collection<Long> instrumentIds, final List<QueryParameter> queryParams, final ScreenerResult result);
	public void getScreeningResults(final Collection<Long> instrumentIds, final List<QueryParameter> queryParams, final ScreenerResult result, final Date runDate );

}
