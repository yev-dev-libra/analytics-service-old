package com.libra.apollo.analytics.engine.results;

import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public interface Result {
	public AnalyticsType getAnalyticsType();
	public RunType getRunType();
}