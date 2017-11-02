package com.libra.apollo.analytics.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.ApolloAnalytics;

public class AnalyticsRepositoryTest extends AbstractRepositoryTest {

	@Autowired private AnalyticsRepository analyticsRepository;
	
	@Test
	public void testHello() {
		
	}
	
	@Test
	public void shouldGetAListOfAnalytics() {
		ApolloAnalytics analytic = analyticsRepository.findOne(1L);
		assertThat(analyticsRepository.findAll().contains(analytic));
	}
}
