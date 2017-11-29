package com.libra.apollo.analytics.service;

import com.libra.apollo.analytics.engine.request.AnalyticsRequest;
import com.libra.apollo.analytics.engine.response.AnalyticsResponse;

public interface Delegator {

	public AnalyticsResponse delegate(AnalyticsRequest request);
}
