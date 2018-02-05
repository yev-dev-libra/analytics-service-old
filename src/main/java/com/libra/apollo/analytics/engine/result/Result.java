package com.libra.apollo.analytics.engine.result;

import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.RunType;

public interface Result {
	public AnalyticsType getAnalyticsType();
	public RunType getRunType();
}
