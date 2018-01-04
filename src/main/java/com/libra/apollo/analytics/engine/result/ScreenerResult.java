package com.libra.apollo.analytics.engine.result;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.libra.apollo.analytics.engine.core.MergeableAnalytics;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class ScreenerResult implements AnalyticsResult, MergeableAnalytics {

	/**
	 * 
	 */
	private static final long serialVersionUID = 468624570135610833L;

	final private List<ValueDataFieldType> requestedFields;
	final private List<ValueDataFieldType> processedParameters;

	List<Iterable<? extends Serializable>> results; // TODO: create a generic return result value wrapper

	
	public ScreenerResult(List<ValueDataFieldType> requestedFields, List<ValueDataFieldType> processedParameters) {
		super();
		this.requestedFields = requestedFields;
		this.processedParameters = processedParameters;
		this.results = new LinkedList<>();
	}

	@Override
	public AnalyticsType getAnalyticsType() {
		return AnalyticsType.APOLLO_SCREENER;
	}

	@Override
	public RunType getRunType() {
		return RunType.MANUAL;
	}

	@Override
	public boolean isMergeEnabled() {
		return Boolean.TRUE;
	}

	@Override
	public List<ValueDataFieldType> getRequestedFields() {
		return requestedFields;
	}

	@Override
	public List<ValueDataFieldType> getProcessedParameters() {
		return processedParameters;
	}

	@Override
	public void merge(Iterable<? extends Serializable> value) {
		results.add(value);

	}

	@Override
	public List<Iterable<? extends Serializable>> getResults() {
		return results;
	}

}
