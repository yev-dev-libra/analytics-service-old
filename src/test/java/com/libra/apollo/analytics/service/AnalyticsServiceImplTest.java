package com.libra.apollo.analytics.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.libra.apollo.analytics.repository.AnalyticsRepository;

@RunWith(SpringRunner.class)
public class AnalyticsServiceImplTest {

	@MockBean private AnalyticsRepository analyticsRepository;
	
	@Before
	public void setUp() {
		Mockito.when(analyticsRepository.count()).thenReturn(1l);
	}
	
	@Test
	public void testAnalyticsCountShouldBeAtLeast1() {
		
		Long count = analyticsRepository.count();
		assertThat(count).isEqualTo(1L);
		
	}
}
