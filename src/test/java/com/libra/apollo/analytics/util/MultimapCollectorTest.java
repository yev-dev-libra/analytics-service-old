package com.libra.apollo.analytics.util;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Multimap;

public class MultimapCollectorTest {

	@Test
	public void shouldExtractMultimapCollector() {

		List<String> values = Arrays.asList("a", "a", "b", "c");
		Multimap<String, String> multimap = values.stream().collect(MultimapCollector.toMultimap(String::new));
		
		assertThat(multimap.get("a"), org.hamcrest.Matchers.contains("a","a") );

	}
	
	@Test
	public void shouldExtractHashMultimapCollector() {
		
		List<String> values = Arrays.asList("a", "a", "b", "c");
		Multimap<String, String> multimap = values.stream().collect(HashMultimapCollector.toMultimap(String::new));
		
		assertThat(multimap.get("a"), org.hamcrest.Matchers.containsInAnyOrder("a"));
		
		assertThat(multimap.get("b"), org.hamcrest.Matchers.containsInAnyOrder("b"));
		
	}
}
