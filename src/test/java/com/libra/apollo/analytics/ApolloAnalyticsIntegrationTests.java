package com.libra.apollo.analytics;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.repository.AnalyticsRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-it.properties")
public class ApolloAnalyticsIntegrationTests{
	
//	@Autowired
//	private TestRestTemplate restTemplate;
//	
//	@Autowired
//    private MockMvc mvc;
	
	@Autowired private AnalyticsRepository analyticsRepository;
	
	@Test
    public void testListAllAvailableAnalytics() {
		ApolloAnalytics analytic = analyticsRepository.findOne(1L);
		assertThat(analyticsRepository.findAll().contains(analytic));
    }
}