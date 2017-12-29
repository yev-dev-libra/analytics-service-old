package com.libra.apollo.analytics.engine.core;

import java.util.List;

public interface AnalyticsContext {

	public AnalyticsData getAnalyticsData();
	public Operation getOperation();
	public List<ValueDataFieldType> getDataFieldsRegistry();
	
}
