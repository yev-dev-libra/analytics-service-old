package com.libra.apollo.analytics.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.libra.apollo.analytics.dto.PortfolioScreenerResultDTO;
import com.libra.apollo.analytics.dto.PortfolioScreenerResultDTO.PortfolioStockResults;
import com.libra.apollo.analytics.dto.StockScreenerResultDTO;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;



@RunWith(SpringRunner.class)
@WebMvcTest(AnalyticsScreenerRestController.class)
@TestPropertySource(locations = "classpath:application-test.properties", properties = {})
public class AnalyticsScreenerRestControllerTest {


	@Autowired
	private MockMvc mvc;
	

	@MockBean
	private AnalyticsScreenerRestController controller;
	
	
	private static final String ANALYTICS_API_URI = "/screener";
	

	@Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
	
	@Test
	public void testIfAnalyticsApiNodeToScreenForPortfolioDoesNoyExist() throws Exception {
		String analyticsUrl = ANALYTICS_API_URI; 
		mvc.perform(get(analyticsUrl)
				.contentType(APPLICATION_JSON))
				.andExpect(status().is(404));
	}
	
	@Test
	public void testIfAnalyticsApiNodeToScreenForPortfolioExists() throws Exception {
		String analyticsUrl = ANALYTICS_API_URI + "/style/" + 1L + "/portfolios/" + 1L + "," + 2L; 
		
		mvc.perform(get(analyticsUrl)
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void testIfAnalyticsApiNodeReturnsPortfolioStockResults() throws Exception {
		
		String analyticsUrl = ANALYTICS_API_URI + "/style/" + 1L + "/portfolios/" + 1L + "," + 2L; 
		Long styleId = 1L;
		List<Long> portfolioIds = Arrays.asList(1L,2L);
		
		
//		List<ValueDataFieldType> requestParams = Arrays.asList(ValueDataFieldType.STOCK_ID, ValueDataFieldType.STAR_RATING); //Used to map index to value params
//		List<ValueDataFieldType> params = Arrays.asList(ValueDataFieldType.STAR_RATING); 
//		
//		//		Long styleId = 1L;
//		List<Long> portfolioIds = Arrays.asList(1L,2L);

//		Map<Long,List<Long>> stockPortfolio = new HashMap<>();
//		stockPortfolio.put(1L, Arrays.asList(1L,2L));
//		stockPortfolio.put(2L, Arrays.asList(1L));
//		
//		Collection<List<?>> firstResultSlice = new ArrayList<>();
//		
//		List<Object> stockAResult = new ArrayList<>();
//		stockAResult.add(0, 1L);
//		stockAResult.add(1, 0.1);
//		
//		List<Object> stockBResult = new ArrayList<>();
//		stockBResult.add(0, 2L);
//		stockBResult.add(1, 0.5);
//		
//		firstResultSlice.add(stockAResult);
//		firstResultSlice.add(stockBResult);
//		
//		List<List<?>> secondResultSlice = new ArrayList<>();
//		
//		List<Object> stockCResult = new ArrayList<>();
//		stockCResult.add(0, 3L);
//		stockCResult.add(1, 0.15);
//		
//		List<Object> stockDResult = new ArrayList<>();
//		stockDResult.add(0, 4L);
//		stockDResult.add(1, 0.15);
//		
//		secondResultSlice.add(stockCResult);
//		secondResultSlice.add(stockDResult);
//		
//		
//		
//		ScreenerResult results = new ScreenerResult.ScreenerResultBuilder()
//				.setParameters(params)
//				.setRequestedFields(requestParams)
//				.setPortfolioIds(portfolioIds)
//				.build();
//		
//		results.merge(firstResultSlice);
//		results.merge(secondResultSlice);
		
		List<String> requestFields = Arrays.asList("");
		List<String> parameters = Arrays.asList("");
		
		PortfolioScreenerResultDTO dto = new PortfolioScreenerResultDTO();
		dto.setAnalyticsType(AnalyticsType.APOLLO_SCREENER.getName());
		dto.setRunType(RunType.MANUAL.getName());
		dto.setInvestmentStyleName("Apollo Classics");
		dto.setInvestmentStyleId(styleId);
		dto.setParameters(parameters);
		dto.setRequestedFields(requestFields);

		PortfolioStockResults portfolioResults1 = dto.new PortfolioStockResults(1L,Arrays.asList(1L,2L));
		PortfolioStockResults portfolioResults2 = dto.new PortfolioStockResults(2L,Arrays.asList(10L,20L));
		
		dto.setResults(Arrays.asList(portfolioResults1, portfolioResults2));
		
		given(controller.screenForPortfolios(styleId, portfolioIds)).willReturn(new ResponseEntity<PortfolioScreenerResultDTO>(dto, HttpStatus.OK));
		
		mvc.perform(get(analyticsUrl)
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.analyticsType", is(String.valueOf(AnalyticsType.APOLLO_SCREENER.getName()))))
				.andExpect(jsonPath("$.runType", is(String.valueOf(RunType.MANUAL.getName()))))
				.andExpect(jsonPath("$.investmentStyleId", is(1)))
				.andExpect(jsonPath("$.investmentStyleName", is("Apollo Classics")))
				.andExpect(jsonPath("$.requestedFields", hasSize(1)))
				.andExpect(jsonPath("$.parameters", hasSize(1)))
				.andExpect(jsonPath("$.results", hasSize(2))
				);
		
	}
	
	@Test
	public void testIfAnalyticsApiNodeReturnsStockScreenerResults() throws Exception {
		
		Long portfolioId = 1L;
		Long styleId = 1L;
		String analyticsUrl = ANALYTICS_API_URI + "/style/" + styleId + "/portfolio/" + portfolioId;
		List<String> requestFields = Arrays.asList("");
		List<String> parameters = Arrays.asList("");
		
		StockScreenerResultDTO dto = new StockScreenerResultDTO();
		dto.setAnalyticsType(AnalyticsType.APOLLO_SCREENER.getName());
		dto.setRunType(RunType.MANUAL.getName());
		dto.setInvestmentStyleName("Apollo Classics");
		dto.setInvestmentStyleId(styleId);
		dto.setParameters(parameters);
		dto.setRequestedFields(requestFields);
		
		List<Long> stocksIds = Arrays.asList(1L,2L,3L);
		
		dto.setResults(stocksIds);
		
		given(controller.screenForStocks(styleId, portfolioId)).willReturn(new ResponseEntity<StockScreenerResultDTO>(dto, HttpStatus.OK));
		
		mvc.perform(get(analyticsUrl)
				.contentType(APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.analyticsType", is(String.valueOf(AnalyticsType.APOLLO_SCREENER.getName()))))
		.andExpect(jsonPath("$.runType", is(String.valueOf(RunType.MANUAL.getName()))))
		.andExpect(jsonPath("$.investmentStyleId", is(1)))
		.andExpect(jsonPath("$.investmentStyleName", is("Apollo Classics")))
		.andExpect(jsonPath("$.requestedFields", hasSize(1)))
		.andExpect(jsonPath("$.parameters", hasSize(1)))
		.andExpect(jsonPath("$.results", hasSize(3))
				);
		
	}
}
