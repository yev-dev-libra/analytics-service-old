package com.libra.apollo.analytics.engine;

import java.util.Collection;
import java.util.Map;

public interface AnalyticsData {

	public Collection<Value> getValues();

	public Collection<Value> getValuesByInstrumentType(ValueDataFieldType fieldType);

	public Map<ValueDataFieldType, Collection<Value>> getValueEntriesByInstrumentType(ValueDataFieldType fieldType);
}
