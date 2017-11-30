package com.libra.apollo.analytics.rules;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

public class AnalyticsRuleRegistry {
	
	public enum  AnalyticsDefinition{ 
		APOLLO_CLASSICS("Apollo Classics","The Apollo 'Classics' are those that we would be happy adding to at current positions, given the positive combination of value, growth, volatility and momentum"), 
		STOCKS_ON_THE_ROCKS("Stocks on the Rocks","The Apollo 'SotR' are companies that are currently unloved by the market, and in the absence or special situations or the reporting season, should underperform."),
		VALUE_BUY("Value Buy","These are companies trading with a Value Indicator score of 20% or below, meaning that there is a Deep Value opportunity here,  with a upside / downside ratio of 4:1."),
		VALUE_SELL("Value Sell","These are companies trading with a Value Indicator score of 80% or above meaning that the stock looks overpriced in relation to future value, with a downside / upside ratio of 4:1."),
		RERATED("Rerating (Apollo 'Growth')","These are stocks that are being re-rated by the market and the discount rate needing to be applied is low (or falling)"),
		DERATED("Derating (Apollo 'Value')","These are stocks that are being de-rated by the market and the discount rate needing to be applied is high (or rising)"),
		BELOW_12M_PESSIMISTIC("Below 12m Pessimistic","These are stocks trading at a discount to Apollo's most pessimistic 12-month scenario."),
		ABOVE_12M_OPTIMISTIC("Above 12m Optimistic","These are stocks trading at a premium to Apollo's most optimistic 12-month scenario.")
		;
		
		private final String name;
		private final String description;
		
		private AnalyticsDefinition(String name,String description) {
			this.name = name;
			this.description = description;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}
		
		
		
	
	}
	
	private static final Map<AnalyticsDefinition,LinkedList<? extends Specification>> registry = new HashMap<>();
	
	static {
		
	}
	
}
