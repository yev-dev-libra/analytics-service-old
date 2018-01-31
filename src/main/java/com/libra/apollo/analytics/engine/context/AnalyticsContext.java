package com.libra.apollo.analytics.engine.context;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.PortfolioService;

public interface AnalyticsContext {

	public ScreenerRequest getRequest();

	public ScreenerResult getResult();

	public AnalyticsService getAnalyticsService();

	public ConfigurationService getConfigurationService();

	public PortfolioService getPortfolioService();

	public Map<String, String> getProperties();
	
	public Operation getOperation();
	
	public AnalyticsType getAnalyticsType();
	
	public Collection<Long> getPortfolioIds();
	
	public Collection<Long> getStockIds();
	
	public Date screenDate();
	
	public Map<Date,Set<Long>> stampDateWithSotcks();
	
	public boolean isResultMergeable();
}