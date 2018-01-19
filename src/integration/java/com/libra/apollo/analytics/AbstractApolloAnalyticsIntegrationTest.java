package com.libra.apollo.analytics;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.PortfolioService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles({ "integration", "dev" })
@TestPropertySource(locations = "classpath:application-test.properties", properties = {})
public class AbstractApolloAnalyticsIntegrationTest {


	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AnalyticsService analyticsService;
	
	@MockBean
	private ConfigurationService configurationService;
	
	@MockBean
	private PortfolioService portfolioService;
	
	private static final String ANALYTICS_API_URI = "/analytics";
	

	@Test
	public void testScreenForPortfolios() {
		assertTrue(true);
	}
	@Test
	public void testScreenForStocks() {
		assertTrue(true);
	}
}