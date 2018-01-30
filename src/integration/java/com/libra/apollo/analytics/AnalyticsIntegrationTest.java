package com.libra.apollo.analytics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.libra.apollo.analytics.api.AnalyticsScreenerRestController;
import com.libra.apollo.analytics.dto.PortfolioScreenerResultDTO;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

@RunWith(SpringRunner.class)
@SpringBootTest(	webEnvironment = WebEnvironment.RANDOM_PORT, 
				classes = ApolloAnalyticsApplication.class
				)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class AnalyticsIntegrationTest  {

	private static final Long APOLLO_CLASSICS_STYLE_ID = 1L;
	private static final Long STOCKS_ON_THE_ROCKS_STYLE_ID = 2L;
	private static final Long VALUE_BUY_STYLE_ID = 3L;
	private static final Long VALUE_SELL_STYLE_ID = 4L;
	private static final Long APOLLO_GROWTH_STYLE_ID = 5L;
	private static final Long APOLLO_VALUE_STYLE_ID = 6L;
	private static final Long BELOW_12M_PESSIMISTIC = 7L;
	private static final Long ABOVE_12M_OPTIMISTIC = 8L;

	private static final String ANALYTICS_API_URI = "/screen";

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mvc;

	final RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private AnalyticsScreenerRestController screenerRestController;

	@Test
	public void contexLoads() throws Exception {
		assertThat(screenerRestController).isNotNull();
	}

	// @Test
	// public void shouldReturnReturnsPortfolioStockResults() {
	//
	// Long styleId = 1L;
	// List<Long> portfolioIds = Arrays.asList(1L,2L);
	// String analyticsUrl = ANALYTICS_API_URI + "/style/" + styleId +
	// "/portfolios/" + 1L + "," + 2L;
	//
	// PortfolioScreenerResultDTO result =
	// this.restTemplate.getForObject("http://localhost:" + port + analyticsUrl,
	// PortfolioScreenerResultDTO.class);
	// System.out.println(result);
	// }

	@Test
	public void givenStyleIdAndListOfPortfoliosShouldReturnStatus200() throws Exception {

		Long styleId = 1L;
		String analyticsUrl = ANALYTICS_API_URI + "/investment-style/" + styleId + "/portfolios/" + 1L + "," + 2L;

		mvc.perform(get(analyticsUrl).contentType(APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.analyticsType", is(String.valueOf(AnalyticsType.APOLLO_SCREENER.getName()))))
				.andExpect(jsonPath("$.runType", is(String.valueOf(RunType.MANUAL.getName()))))
				.andExpect(jsonPath("$.investmentStyleId", is(1)))
				.andExpect(jsonPath("$.investmentStyleName", is("Apollo Classics")))
//				.andExpect(jsonPath("$.requestedFields", hasSize(1))).andExpect(jsonPath("$.parameters", hasSize(1)))
//				.andExpect(jsonPath("$.portfolios", hasSize(2)))
						;
	}
}
