package com.libra.apollo.analytics.engine.result;

import java.io.Serializable;
import java.util.List;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public interface AnalyticsResult extends Serializable {
	
	public AnalyticsType getAnalyticsType();
	public RunType getRunType();
	public List<ValueDataFieldType> getRequestedFields();
	public List<ValueDataFieldType> getParameters();
	public List<Iterable<?>> getResults();
	//TODO: add errors collectors
	

}
