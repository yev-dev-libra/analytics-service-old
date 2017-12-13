package com.libra.apollo.analytics.service;

import com.libra.apollo.analytics.engine.request.AnalyticsRequest;
import com.libra.apollo.analytics.engine.result.AnalyticsResult;

public interface Delegator {

	public AnalyticsResult delegate(AnalyticsRequest request);
}
