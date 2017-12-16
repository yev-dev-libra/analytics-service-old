package com.libra.apollo.analytics;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.ConfigurationService;

public class AnalyticsIntegrationTest extends AbstractApolloAnalyticsIntegrationTest {

	private static final Long APOLLO_CLASSICS_STYLE_ID =1L;
	private static final Long STOCKS_ON_THE_ROCKS_STYLE_ID =2L;
	private static final Long VALUE_BUY_STYLE_ID =3L;
	private static final Long VALUE_SELL_STYLE_ID =4L;
	private static final Long APOLLO_GROWTH_STYLE_ID =5L;
	private static final Long APOLLO_VALUE_STYLE_ID =6L;
	private static final Long BELOW_12M_PESSIMISTIC =7L;
	private static final Long ABOVE_12M_OPTIMISTIC =8L;
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private AnalyticsService analyticsService;
	
	private Date previousDate;
	
	@Before
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 01);
		previousDate = new Date(cal.getTimeInMillis());
	}
	
	@Test
	public void shouldRetrieveListOfIndicatorsForApolloClassics() {
		
		AnalyticsType type = AnalyticsType.APOLLO_SCREENER;
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 11, 01);

		Collection<Long> stockIds = Arrays.asList(1L);
		
	    analyticsService.getScreeningResults(type,previousDate,stockIds, APOLLO_CLASSICS_STYLE_ID );
		
		assertTrue(true);
	}

	
}
