package com.libra.apollo.analytics.engine.context;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.core.task.AsyncTaskExecutor;

import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.CalendarService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.PortfolioService;


public class PortfolioScreenerContext implements AnalyticsContext {

	public enum PropertyName{
		
		BATCH_SIZE("analytics.async.batch-size") ;
		
		private final String name;
		
		private PropertyName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		
		
	}
	
	private final AnalyticsService analyticsService;
	private final ConfigurationService configurationService;
	private final PortfolioService portfolioService;
	private final AsyncTaskExecutor executorService;
	
	private final Map<String,String> properties;
	private final Operation operation;
	private final ScreenerRequest request;
	private final CalendarService calendarService;
	
	private Map<Long,Collection<Long>> stockPortfolios;
	
	private ScreenerResult result;
	
	private Map<Date,Set<Long>> maxStampDatePerStock;
	
	
	public PortfolioScreenerContext(	final AnalyticsService analyticsService, 
									final ConfigurationService configurationService, 
									final PortfolioService portfolioService, 
									final AsyncTaskExecutor executorService, 
									CalendarService calendarService,
									final Operation operation, 
									final Map<String,String> properties, 
									final ScreenerRequest request) {
		
		this.analyticsService = analyticsService;
		this.configurationService = configurationService;
		this.portfolioService = portfolioService;
		this.executorService = executorService;
		this.calendarService = calendarService;
		this.operation = operation;
		this.properties = properties;
		this.request = request;
	}


	@Override
	public ScreenerRequest getRequest() {
		return request;
	}


	@Override
	public ScreenerResult getResult() {
		return result;
	}


	public void setResult(ScreenerResult result) {
		this.result = result;
	}

	

	public Map<Long, Collection<Long>> getStockPortfolios() {
		return stockPortfolios;
	}


	public void setStockPortfolios(Map<Long, Collection<Long>> stockPortfolios) {
		this.stockPortfolios = stockPortfolios;
	}
	
	

	public Map<Date, Set<Long>> getMaxStampDatePerStock() {
		return maxStampDatePerStock;
	}


	public void setMaxStampDatePerStock(Map<Date, Set<Long>> maxStampDatePerStock) {
		this.maxStampDatePerStock = maxStampDatePerStock;
	}


	@Override
	public AnalyticsService getAnalyticsService() {
		return analyticsService;
	}


	@Override
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	
	@Override
	public PortfolioService getPortfolioService() {
		return portfolioService;
	}


	public AsyncTaskExecutor getExecutorService() {
		return executorService;
	}



	public CalendarService getCalendarService() {
		return calendarService;
	}


	@Override
	public Map<String, String> getProperties() {
		return properties;
	}


	@Override
	public Operation getOperation() {
		return operation;
	}


	@Override
	public AnalyticsType getAnalyticsType() {
		return AnalyticsType.APOLLO_SCREENER;
	}
	
	@Override
	public Collection<Long> getStockIds() {
		return this.stockPortfolios.keySet();
	}


	@Override
	public Collection<Long> getPortfolioIds() {
		Set<Long> portfolioIds = new HashSet<>();
		
		for(Map.Entry<Long,Collection<Long>> port : stockPortfolios.entrySet()) {
			portfolioIds.addAll(port.getValue());
		}
		
		return portfolioIds;
	}


	@Override
	public Date screenDate() {
		return null;
	}

	@Override
	public boolean isResultMergeable() {
		return Boolean.TRUE;
	}


	@Override
	public Map<Date, Set<Long>> stampDateWithSotcks() {
		return maxStampDatePerStock;
	}

	
	
}
