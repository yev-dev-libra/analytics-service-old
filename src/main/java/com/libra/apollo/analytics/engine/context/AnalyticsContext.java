package com.libra.apollo.analytics.engine.context;

import java.util.Map;

import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.PortfolioService;

public interface AnalyticsContext {

	ScreenerRequest getRequest();

	ScreenerResult getResult();

	AnalyticsService getAnalyticsService();

	ConfigurationService getConfigurationService();

	PortfolioService getPortfolioService();

	Map<String, String> getProperties();
	
	public Operation getOperation();

}