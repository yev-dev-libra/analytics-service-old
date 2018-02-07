package com.libra.apollo.analytics.engine.context;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.task.AsyncTaskExecutor;

import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.CalendarService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.GroupsService;

public class PortfolioScreenerContextTest {

	private AnalyticsContext analyticsContext;

	@Before
	public void init() {
		this.analyticsContext = createAnalyticsContext();
	}
	
	@Test
	public void testContextSetup() {
		assertEquals(Operation.SCREEN_FOR_PORTFOLIOS, analyticsContext.getOperation());
	}

	private AnalyticsContext createAnalyticsContext() {
		Map<String, String> properties = new HashMap<>();
		return new PortfolioScreenerContext(mock(AnalyticsService.class), mock(ConfigurationService.class), mock(GroupsService.class), mock(AsyncTaskExecutor.class), mock(CalendarService.class), Operation.SCREEN_FOR_PORTFOLIOS, properties, mock(ScreenerRequest.class));
	}
}
