package com.libra.apollo.analytics.engine.request;

import com.libra.apollo.analytics.engine.Operation;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class ScreenerRequest implements AnalyticsRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1863827676275650547L;

	
	@Override
	public AnalyticsType getAnalyticsType() {
		return AnalyticsType.APOLLO_ANALYZER;
	}

	@Override
	public RunType getRunType() {
		return RunType.ON_DEMAND;
	}

	@Override
	public Operation getOperation() {
		return Operation.ANALYZE;
	}

}
