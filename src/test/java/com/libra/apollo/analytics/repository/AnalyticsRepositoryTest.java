package com.libra.apollo.analytics.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;

public class AnalyticsRepositoryTest extends AbstractRepositoryTest {

	@Autowired private AnalyticsRepository analyticsRepository;
	
	
	@Test
	public void shouldGetAListOfAnalytics() {
		ApolloAnalytics analytic = analyticsRepository.findOne(1L);
		assertThat(analyticsRepository.findAll().contains(analytic));
	}
	
	@Test
	public void shouldFindByType() {
		List<ApolloAnalytics> analytics =  analyticsRepository.findByType(AnalyticsType.APOLLO_SCREENER);
		assertThat(analytics.size()>0);
	}
}
