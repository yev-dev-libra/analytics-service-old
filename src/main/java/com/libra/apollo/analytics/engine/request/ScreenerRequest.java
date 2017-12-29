package com.libra.apollo.analytics.engine.request;

import java.util.Date;

import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class ScreenerRequest implements AnalyticsRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1863827676275650547L;

	@Override
	public AnalyticsType getAnalyticsType() {
		return AnalyticsType.APOLLO_SCREENER;
	}

	@Override
	public RunType getRunType() {
		return RunType.MANUAL;
	}

	@Override
	public Operation getOperation() {
		return Operation.SCREEN;
	}

	public Date getRunDate() {
		return null;
	}

}
