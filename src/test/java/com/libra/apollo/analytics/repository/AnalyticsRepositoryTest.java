package com.libra.apollo.analytics.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class AnalyticsRepositoryTest extends AbstractRepositoryTest {

	@Autowired private AnalyticsRepository analyticsRepository;
	
	
	@Test
	public void should_Find_Analytics_By_Id() {
		ApolloAnalytics val = analyticsRepository.findOne(1L);
		assertThat(val, is(notNullValue()) );
	}
	
	@Test
	public void should_Find_By_Type() {
		List<ApolloAnalytics> analytics =  analyticsRepository.findAllByType(AnalyticsType.APOLLO_SCREENER);
		assertThat(analytics.size(), greaterThan(0));
	}
	@Test
	public void should_Find_By_Type_And_Operation_And_RunType() {
		List<ApolloAnalytics> analytics =  analyticsRepository.findAllByTypeAndByRunType(AnalyticsType.APOLLO_SCREENER, RunType.ON_DEMAND);
		assertThat(analytics.size(), greaterThan(0));
	}
	
	@Test
	public void should_Find_By_Type_And_Operation_Or_RunType() {
		List<ApolloAnalytics> analytics =  analyticsRepository.findAllByTypeOrByRunType(AnalyticsType.APOLLO_SCREENER, RunType.ON_DEMAND);
		assertThat(analytics.size(), greaterThan(0));
	}
}
