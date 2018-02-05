package com.libra.apollo.analytics.engine.request;

import java.io.Serializable;

import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.RunType;

public interface AnalyticsRequest extends Serializable {

	public AnalyticsType getAnalyticsType();
	public RunType getRunType();
	public Operation getOperation();
}
