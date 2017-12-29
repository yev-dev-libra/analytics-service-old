package com.libra.apollo.analytics.service;

import java.util.List;
import com.libra.apollo.analytics.engine.request.AnalyticsConfigurationRequest;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public interface ConfigurationService {

	public List<ApolloAnalytics> getAnalytics();
	public List<ApolloAnalytics> getAnalyticsById(Long id);
	public List<ApolloAnalytics> getAnalyticsByName(String name);
	public List<String> getAnalyticsNames();
	public List<ApolloAnalytics> getAnalyticsByType(AnalyticsType type);
	public List<ApolloAnalytics> getAnalyticsByRunType(RunType type);
	
	public List<ApolloAnalytics> getAnalyticsViews(AnalyticsConfigurationRequest request);
	public List<InvestmentStyle> getInvestmentStyles(Long analyticsViewId);


}
