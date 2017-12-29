package com.libra.apollo.analytics.engine.core;

import java.util.List;

public class AnalyzerAnalyticsContext implements AnalyticsContext {

	private AnalyticsData analyticsDataCache;
	private Operation operation;
	private List<ValueDataFieldType> DataFieldsRegistry;

	@Override
	public AnalyticsData getAnalyticsData() {
		return analyticsDataCache;
	}

	@Override
	public Operation getOperation() {
		return operation;
	}

	@Override
	public List<ValueDataFieldType> getDataFieldsRegistry() {
		return DataFieldsRegistry;
	}

}
