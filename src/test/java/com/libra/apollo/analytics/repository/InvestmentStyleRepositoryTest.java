package com.libra.apollo.analytics.repository;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.InvestmentStyleParameter;
import com.libra.apollo.analytics.entity.QueryParameter;

public class InvestmentStyleRepositoryTest extends AbstractRepositoryTest {

	@Autowired private InvestmentStyleRepository repository;
	
	
	@Test
	public void shouldRetrieveAllInvestmentStyle() {
		List<InvestmentStyle> allInvStyles = repository.findAll();
		assertThat(allInvStyles.size(), greaterThan(1));
	}
	
	@Test
	public void when_InvestmentStyle_Id_Passed_Should_Retrieve_InvestmentStyleParameters() {
		Optional<InvestmentStyle> invstStyleOptional = repository.findById(1L);
		InvestmentStyle invstStyle = invstStyleOptional.get();
		SortedSet<InvestmentStyleParameter> conditions = invstStyle.getInvestmentStyleParameters();
		assertThat(conditions.size(), greaterThan(1));
	}
	
	
	@Test
	public void when_InvestmentStyle_Id_Passed_Should_Retrieve_Optional_InvestmentStyle() {
		Optional<InvestmentStyle> invstStyleOptional = repository.findById(1L);
		assertThat(invstStyleOptional.get(), is(notNullValue()));
	}
	
	
	@Test
	public void when_AnalyticsView_Id_Passed_Should_Retrieve_All_InvestmentStyles() {
		List<InvestmentStyle> invstStyles = repository.findAllByView(1L);
		assertThat(invstStyles.size(), greaterThan(1));
	}
	@Test
	public void should_Retrieve_InvestmentStyleParameters_In_Expected_Priority_Order() {
		Optional<InvestmentStyle> invstStyleOptional = repository.findById(1L);
		InvestmentStyle invstStyle = invstStyleOptional.get();
		SortedSet<InvestmentStyleParameter> conditions = invstStyle.getInvestmentStyleParameters();

		// a sanity check to validate correct sorting
		Map<Integer,QueryParameter> priorityWithConditions = new LinkedHashMap<>();
		
		
		for(InvestmentStyleParameter invstCondition : conditions) {
			priorityWithConditions.put(invstCondition.getPriority().getPriority(), invstCondition.getParameter());
		}

		int i = 1;
		for(Map.Entry<Integer, QueryParameter> entry : priorityWithConditions.entrySet()) {
			System.out.println("Key value " + entry.getKey());
			assertThat(entry.getKey(),equalTo(i));
			i++;
		}
	}
	
	@Test
	public void should_Get_List_Of_Parameters() {
		List<QueryParameter> parameters = repository.findQueryParametersById(1L);
		assertThat(parameters.size(), greaterThan(1));
	}
	
	
}
