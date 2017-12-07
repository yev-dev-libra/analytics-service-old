package com.libra.apollo.analytics.service;

import java.util.List;

import com.libra.apollo.analytics.engine.request.AnalyticsViewConfigurationRequest;
import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.InvestmentStyle;

public interface ConfigurationService {

	public List<AnalyticsView> getInvestmentStyles(AnalyticsViewConfigurationRequest request);
	public List<InvestmentStyle> getInvestmentStyles(Long analyticsViewId);


}
