package com.libra.apollo.analytics.engine.result;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.InvestmentStyle;


public interface AnalyticsResult extends Serializable, Result {
	public InvestmentStyle getInvestmentStyle();
	public List<ValueDataFieldType> getRequestedFields();
	public List<ValueDataFieldType> getParameters();
	public Collection<?> getResults();
	public void addResults(Collection<List<?>> values);
	//TODO: add errors collectors
	

}
