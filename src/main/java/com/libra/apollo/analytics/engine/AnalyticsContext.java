package com.libra.apollo.analytics.engine;

import java.util.List;

public interface AnalyticsContext {

	public AnalyticsData getAnalyticsData();
	public Operation getOperation();
	public List<ValueDataFieldType> getDataFieldsRegistry();
	
}
