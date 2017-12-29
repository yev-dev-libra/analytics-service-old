package com.libra.apollo.analytics.engine.task;

import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public interface Task {

	public AnalyticsType getAnalyticsType();
	public RunType getRunType();
	public Operation getOperation();
}
