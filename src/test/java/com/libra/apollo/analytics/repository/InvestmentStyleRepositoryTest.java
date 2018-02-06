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
import com.libra.apollo.analytics.engine.core.Value;
import com.libra.apollo.analytics.entity.FieldParameter;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.InvestmentStyleParameter;
import com.libra.apollo.analytics.entity.Priority;
import com.libra.apollo.analytics.entity.QueryParameter;

public class InvestmentStyleRepositoryTest extends AbstractRepositoryTest {

	@Autowired private InvestmentStyleRepository repository;
	
	private static final Long APOLLO_CLASSICS_STYLE_ID =1L;
	private static final Long STOCKS_ON_THE_ROCKS_STYLE_ID =2L;
	private static final Long VALUE_BUY_STYLE_ID =3L;
	private static final Long VALUE_SELL_STYLE_ID =4L;
	private static final Long APOLLO_GROWTH_STYLE_ID =5L;
	private static final Long APOLLO_VALUE_STYLE_ID =6L;
	private static final Long BELOW_12M_PESSIMISTIC =7L;
	private static final Long ABOVE_12M_OPTIMISTIC =8L;
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
		Map<Integer,Value> priorityWithConditions = new LinkedHashMap<>();
		
		
		for(InvestmentStyleParameter invstCondition : conditions) {
			priorityWithConditions.put(invstCondition.getPriority().getPriority(), invstCondition.getQueryParameter());
		}

		int i = 1;
		for(Map.Entry<Integer, Value> entry : priorityWithConditions.entrySet()) {
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
	
	@Test
	public void should_Get_Map_Of_Parameters_With_Priority() {
		List<Map<QueryParameter,Priority>> parameters = repository.findQueryParametersByIdWithPriority(APOLLO_CLASSICS_STYLE_ID);
		assertThat(parameters.size(), greaterThan(1));
	}
	@Test
	public void should_Get_SortedSet_Of_Parameters() {
		SortedSet<QueryParameter> parameters = repository.findQueryParametersByIdSorted(APOLLO_CLASSICS_STYLE_ID);
		assertThat(parameters.size(), greaterThan(1));
	}
	@Test
	public void should_Get_SortedSet_Of_FieldParameters() {
		SortedSet<FieldParameter> parameters = repository.findFieldParametersById(APOLLO_CLASSICS_STYLE_ID);
		assertThat(parameters.size(), greaterThan(1));
	}
	
	
	@Test
	public void should_Count_Apollo_Classics_Parameters() {
		
		long cnt = repository.countQueryParametersById(APOLLO_CLASSICS_STYLE_ID);
		assertThat(cnt,equalTo(6L));
		
	}
	
	@Test
	public void should_Count_Stocks_on_The_Rocks_Parameters() {
	}
	
	@Test
	public void should_Count_Value_Buys_Parameters() {
	}
	
	@Test
	public void should_Count_Value_Sells_Parameters() {
	}
	
	@Test
	public void should_Count_Apollo_Growth_Parameters() {
	}
	
	@Test
	public void should_Count_Apollo_Value_Parameters() {
	}
	
	@Test
	public void should_Count_Long_Term_Optimistic_Parameters() {
	}
	
	@Test
	public void should_Count_Long_Term_Pessimistic_Parameters() {
	}
	
	@Test
	public void should_Get_List_Apollo_Classics_Parameters() {
		//5/4/3-star Green, Rising 1m FV and IV, trading within FV range
		
	}
	
	@Test
	public void should_Get_List_Stocks_on_The_Rocks_Parameters() {
	}
	
	@Test
	public void should_Get_List_Value_Buys_Parameters() {
	}
	
	@Test
	public void should_Get_List_Value_Sells_Parameters() {
	}
	
	@Test
	public void should_Get_List_Apollo_Growth_Parameters() {
	}
	
	@Test
	public void should_Get_List_Apollo_Value_Parameters() {
	}
	
	@Test
	public void should_Get_List_Long_Term_Optimistic_Parameters() {
	}
	
	@Test
	public void should_Get_List_Long_Term_Pessimistic_Parameters() {
	}
	
	
	
}
