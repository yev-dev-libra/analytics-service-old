package com.libra.apollo.analytics.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.Parameter;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.InvestmentStyleCondition;

public class InvestmentStyleRepositoryTest extends AbstractRepositoryTest {

	@Autowired private InvestmentStyleRepository repository;
	
	
	@Ignore
	@Test
	public void shouldBeSortedByPriorities() {
		
	}
	
	@Test
	public void shouldRetrieveAllInvestmentStyle() {
		List<InvestmentStyle> allInvStyles = repository.findAll();
		assertThat(allInvStyles, hasSize(1));
	}
	
	@Test
	public void shouldConstructConditionsForInvestmentStyle() {
		Optional<InvestmentStyle> invstStyleOptional = repository.findById(1L);
		InvestmentStyle invstStyle = invstStyleOptional.get();
		SortedSet<InvestmentStyleCondition> conditions = invstStyle.getInvestmentStyleConditions();
		assertThat(conditions, hasSize(3));
		
		// a sanity check to validate correct sorting
		Map<Integer,Parameter> priorityWithConditions = new LinkedHashMap<>();
		
		
		for(InvestmentStyleCondition invstCondition : conditions) {
			priorityWithConditions.put(invstCondition.getPriority().getPriority(), invstCondition.getCondition());
		}

		int i = 1;
		for(Map.Entry<Integer, Parameter> entry : priorityWithConditions.entrySet()) {
			System.out.println("Key value " + entry.getKey());
			assertThat(entry.getKey(),equalTo(i));
			i++;
		}
	}
	
	
}
