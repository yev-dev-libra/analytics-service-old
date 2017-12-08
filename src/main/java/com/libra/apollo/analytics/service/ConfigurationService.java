package com.libra.apollo.analytics.service;

import java.util.List;

import com.libra.apollo.analytics.engine.request.AnalyticsConfigurationRequest;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.InvestmentStyle;

public interface ConfigurationService {

	public List<ApolloAnalytics> getAnalyticsViews(AnalyticsConfigurationRequest request);
	public List<InvestmentStyle> getInvestmentStyles(Long analyticsViewId);


}
