package com.libra.apollo.analytics.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.AnalyticsView;

public class AnalyticsViewRepositoryTest extends AbstractRepositoryTest {

	@Autowired private AnalyticsViewRepository repository;
	
	@Test
	public void should_Retrieve_All_AnalyticsViews() {
		List<AnalyticsView> views = repository.findAll();
		
		assertThat(views.size(), greaterThan(0));
		System.out.println("Size is " + views.size());
		
	}
	
	@Test
	public void should_Retrieve_All_AnalyticsViews_By_Analytics_Ids() {
		
		List<AnalyticsView> views = repository.findAllByAnalyticsIn(Arrays.asList(1L));
		
		assertThat(views.size(), greaterThan(0));
		System.out.println("Size is " + views.size());
		
	}
}
