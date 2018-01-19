package com.libra.apollo.analytics.api;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(AnalyticsRestController.class)
//@TestPropertySource(locations = "classpath:application-test.properties", properties = {})
public class AnalyticsRestControllerTest {


	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AnalyticsRestController controller;
	
	
	private static final String ANALYTICS_API_URI = "/analytics";
	

	@Test
	public void testScreenForPortfolios() {
	}
	@Test
	public void testScreenForStocks() {
	}
}
