package com.libra.apollo.analytics.engine.context;

import java.util.Map;

import org.springframework.core.task.AsyncTaskExecutor;

import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.CalendarService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.GroupsService;

public class AnalyticsContextFactory {

	public static AnalyticsContext getContext(final AnalyticsService analyticsService,
			final ConfigurationService configurationService, final GroupsService portfolioService,
			final AsyncTaskExecutor executorService, final CalendarService calendarService, final Map<String, String> properties,
			final ScreenerRequest request) {

		final Operation operation = request.getOperation();
		
		AnalyticsContext context = null;
		switch (operation) {
		case SCREEN_FOR_PORTFOLIOS: {
			context = new PortfolioScreenerContext(analyticsService, configurationService, portfolioService, executorService, calendarService, Operation.SCREEN_FOR_PORTFOLIOS, properties, request);

		}
			break;
		case SCREEN_FOR_STOCKS: {
			
		}
			;
			break;
		default:
			throw new UnsupportedOperationException();
		}

		return context;
	}
}
