package com.libra.apollo.analytics;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.service.AnalyticsService;

public class AnalyticsIntegrationTest extends AbstractApolloAnalyticsIntegrationTests {

	@Autowired
	private AnalyticsService analyticsService;

	@Before
	public void setUp() {
//		
//		ParameterCondition valueIndicatorConditionLessThanOrEquals20 = new ParameterCondition();
//		valueIndicatorConditionLessThanOrEquals20.set(ParameterDefinition.INTRINSIC_VALUE_PCT);
//		valueIndicatorConditionLessThanOrEquals20.setOperator(Operation.LESS_THAN_OR_EQUAL);
//		valueIndicatorConditionLessThanOrEquals20.setParameter(0.2); 
//		
//		Condition valueIndicatorConditionLessThan20 = new Condition();
//		valueIndicatorConditionLessThan20.setCriteriaDefinition(ParameterDefinition.INTRINSIC_VALUE_PCT);
//		valueIndicatorConditionLessThan20.setOperator(Operation.LESS_THAN);
//		valueIndicatorConditionLessThan20.setParameter(0.2); 
//		
//		Condition valueIndicatorConditionGreaterOrEqualsThan20 = new Condition();
//		valueIndicatorConditionGreaterOrEqualsThan20.setCriteriaDefinition(ParameterDefinition.INTRINSIC_VALUE_PCT);
//		valueIndicatorConditionGreaterOrEqualsThan20.setOperator(Operation.GREATER_THAN_OR_EQUAL);
//		valueIndicatorConditionGreaterOrEqualsThan20.setParameter(0.2); 
//		
//		Condition valueIndicatorConditionGreaterThan20 = new Condition();
//		valueIndicatorConditionGreaterThan20.setCriteriaDefinition(ParameterDefinition.INTRINSIC_VALUE_PCT);
//		valueIndicatorConditionGreaterThan20.setOperator(Operation.GREATER_THAN);
//		valueIndicatorConditionGreaterThan20.setParameter(0.2); 
//		
//		
//		
//		Condition investableOpportunityCondition = new Condition();
//		investableOpportunityCondition.setCriteriaDefinition(ParameterDefinition.IV_1M);
//		
//		
//		Condition tradingOpportunityCondition = new Condition();
//		tradingOpportunityCondition.setCriteriaDefinition(ParameterDefinition.IV_1M);
//
//		//Setting up investment styles
//		
//		//--- Investment Style Condition Value Longs ---// 
//		
//		InvestmentStyleCondition valueLongsInvestmentStyleCondition = new InvestmentStyleCondition();
//		valueLongsInvestmentStyleCondition.setCondition(valueIndicatorConditionLessThanOrEquals20);
//		valueLongsInvestmentStyleCondition.setType(ConditionType.PARAMETER);
//		valueLongsInvestmentStyleCondition.setOperator(Operation.AND);
//		
//		Priority first = new Priority();
//		first.setPriority(1);Ê
//		
//		valueLongsInvestmentStyleCondition.setPriority(first);
//		
		
		
		//valueIndicatorCondition <= 0.2
		
		//--- Criteria for Value Sells ---// 
		
		
		//--- Criteria for Apollo Classics ---// 
		
		
		//--- Criteria for Stocks on the Rocks ---// 
		
	}
	
	@Test
	public void testListAllAvailableAnalytics() {
		List<ApolloAnalytics> basicAnalytics = analyticsService.getAllApolloAnalytics();
		assertTrue(basicAnalytics.size() > 0);
	}

	@Ignore
	@Test
	public void testListAllAvailableAnalyticsByType() {
		List<ApolloAnalytics> basicAnalytics = analyticsService
				.getApolloAnalyticsByAnalyticsType(AnalyticsType.BASIC_SCREENER);
	}
}
