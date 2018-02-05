package com.libra.apollo.analytics.engine.result;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.List;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.InvestmentStyle;


public interface AnalyticsResult extends Serializable, Result {
	public InvestmentStyle getInvestmentStyle();
	public List<ValueDataFieldType> getRequestedFields();
	public List<ValueDataFieldType> getParameters();
	public List<EnumMap<ValueDataFieldType,?>> getResultValues();
	public void addValueResults(List<EnumMap<ValueDataFieldType,?>> values);
	//TODO: add errors collectors
	

}
