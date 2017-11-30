package com.libra.apollo.analytics.service;

import org.springframework.stereotype.Component;

import com.libra.apollo.analytics.engine.request.AnalyticsRequest;
import com.libra.apollo.analytics.engine.response.AnalyticsConfigurationResponse;
import com.libra.apollo.analytics.engine.response.AnalyticsResponse;
import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.InvestmentStyle;

@Component("viewsConfigDelegatorStub")
public class ConfigurationsDelegatorStub implements Delegator {

	@Override
	public AnalyticsResponse delegate(AnalyticsRequest request) {
		
		final AnalyticsConfigurationResponse response = new AnalyticsConfigurationResponse();
		
		final AnalyticsView apolloTemplates = new AnalyticsView();
		
		final InvestmentStyle apolloClassics = new InvestmentStyle();
		
		
		return response;
	}

}
