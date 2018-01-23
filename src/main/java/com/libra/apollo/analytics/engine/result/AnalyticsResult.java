package com.libra.apollo.analytics.engine.result;

import java.io.Serializable;
import java.util.List;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;


public interface AnalyticsResult extends Serializable, Result {
	
	public List<ValueDataFieldType> getRequestedFields();
	public List<ValueDataFieldType> getParameters();
	public List<?> getResults();
	//TODO: add errors collectors
	

}
