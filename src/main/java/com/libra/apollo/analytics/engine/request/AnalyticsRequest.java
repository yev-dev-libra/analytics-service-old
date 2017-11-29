package com.libra.apollo.analytics.engine.request;

import java.io.Serializable;

import com.libra.apollo.analytics.engine.Operation;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public interface AnalyticsRequest extends Serializable {

	public AnalyticsType getAnalyticsType();
	public RunType getRunType();
	public Operation getOperation();
}
