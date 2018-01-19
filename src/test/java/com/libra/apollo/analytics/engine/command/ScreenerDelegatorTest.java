package com.libra.apollo.analytics.engine.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.libra.apollo.analytics.engine.context.ScreenerContext;
import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.PortfolioService;

public class ScreenerDelegatorTest {

	private static final Long APOLLO_CLASSICS_STYLE_ID =1L;
	private static List<Long> portfolioIds = Arrays.asList(1L,2L);
	private static Map<Long,List<Long>> stockPortofioIds = new HashMap<>();
	private static Set<Long> instrumentIds = stockPortofioIds.keySet();
	
	private ScreenerContext analyticsContext;
	

	@Before
	public void init() {
		this.analyticsContext = createAnalyticsContext();
		stockPortofioIds.put(1L, Arrays.asList(1L));
		stockPortofioIds.put(2L, Arrays.asList(1L));
		stockPortofioIds.put(3L, Arrays.asList(1L,2L));
	}
	
	@Test
	public void testScreenerDelegatorInnerCommandsSequence() {
		
		ScreenerDelegator delegator = new ScreenerDelegator(analyticsContext);
		
		Command portfolioEnrichment = new PortfolioEnrichmentCommand(analyticsContext);
		
		Command screenerCommand = new ScreenerCommand(analyticsContext);
		
		delegator.add(portfolioEnrichment);
		delegator.add(screenerCommand);
		
		//Simulate execution
		
		Command firstCommand = delegator.next();
		assertTrue( firstCommand instanceof PortfolioEnrichmentCommand );
		
		Command secondCommand = delegator.next();
		assertTrue( secondCommand instanceof ScreenerCommand );
	}
	
	@Test
	public void testScreenerFlow() {
		
		ScreenerDelegator delegator = new ScreenerDelegator(analyticsContext);
		
		Command portfolioEnrichment = new PortfolioEnrichmentCommand(analyticsContext);
		
		Command screenerCommand = new ScreenerCommand(analyticsContext);
		
		delegator.add(portfolioEnrichment);
		delegator.add(screenerCommand);
		
		delegator.execute();
		
	}
	
	private ScreenerContext createAnalyticsContext() {
		Map<String, String> properties = new HashMap<>();
		
		ScreenerRequest mockRequest = mock(ScreenerRequest.class);
		ScreenerResult mockResult = mock(ScreenerResult.class);
		AnalyticsService mockAnalyticsService = mock(AnalyticsService.class);
		ConfigurationService mockConfigurationService = mock(ConfigurationService.class);
		PortfolioService mockPortfolioService = mock(PortfolioService.class);
		
		ScreenerContext context = new ScreenerContext(mockAnalyticsService, mockConfigurationService, mockPortfolioService, Operation.SCREEN, properties);
		
		when(mockRequest.getAnalyticsType()).thenReturn(AnalyticsType.APOLLO_SCREENER);
		when(mockRequest.getInvestmentStyleId()).thenReturn(APOLLO_CLASSICS_STYLE_ID);
		when(mockRequest.getPortfolioIds()).thenReturn(portfolioIds);
		when(mockRequest.getOperation()).thenReturn(Operation.SCREEN);
		
		when(mockPortfolioService.getStockIdsWithPortfolios(portfolioIds)).thenReturn(stockPortofioIds);
		
		when(mockConfigurationService.getInvestmentStylesQueryParameters(APOLLO_CLASSICS_STYLE_ID)).thenReturn(Collections.EMPTY_LIST);
		
		doNothing().when(mockAnalyticsService).getScreeningResults(Mockito.anyCollection(), Mockito.any(), mockResult, Mockito.anyObject());
		
		
		return context;
	}
}
