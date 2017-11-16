package com.libra.apollo.analytics;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.repository.LibraStockIndicatorRepositoryJpa;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-dev.properties", 
					properties = {})

public class AbstractApolloAnalyticsIntegrationTest{
	
	private static final Logger log = LoggerFactory.getLogger(AbstractApolloAnalyticsIntegrationTest.class);
	
	@Autowired
	private LibraStockIndicatorRepositoryJpa repository;

	@PersistenceContext
	private EntityManager entityManager;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void testQuery() {
		LibraStockIndicator stockIndicators = repository.findOne(156261081L);
		System.out.println(stockIndicators);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date date = new Date(cal.getTimeInMillis());
		List<LibraStockIndicator> indicators = repository.findByStampDate(date);
		System.out.println(indicators);
	}

}