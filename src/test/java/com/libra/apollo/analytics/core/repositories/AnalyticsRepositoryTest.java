package com.libra.apollo.analytics.core.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.libra.apollo.analytics.core.entities.Analytics;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AnalyticsRepositoryTest {

	@Autowired AnalyticsRepository analyticsRepository;
	
	@Test
	public void shouldGetAListOfAnalytics() {
		Analytics analytic = analyticsRepository.findOne(1L);
		assertThat(analyticsRepository.findAll().contains(analytic));
	}
}
