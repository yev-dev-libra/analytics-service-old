package com.libra.apollo.analytics.service;

import org.springframework.stereotype.Component;

import com.libra.apollo.analytics.engine.request.AnalyticsRequest;
import com.libra.apollo.analytics.engine.response.AnalyticsConfigurationResponse;
import com.libra.apollo.analytics.engine.response.AnalyticsResponse;

@Component("investmentStylesConfigDelegatorStub")
public class ConfigurationsDelegatorStub implements Delegator {

	@Override
	public AnalyticsResponse delegate(AnalyticsRequest request) {
		
		AnalyticsConfigurationResponse response = new AnalyticsConfigurationResponse();
		
		return response;
	}

}
