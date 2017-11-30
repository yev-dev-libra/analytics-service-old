package com.libra.apollo.analytics.engine.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class AnalyticsConfigurationResponse implements AnalyticsResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6327485650244147942L;

	private List<AnalyticsView> analyticsViews = new ArrayList<>();

	public void addAnalyticsView(AnalyticsView view) {
		analyticsViews.add(view);
	}
	
	@Override
	public Collection<AnalyticsView> getAnalyticsViews() {
		return analyticsViews;
	}

	@Override
	public AnalyticsType getAnalyticsType() {
		return AnalyticsType.APOLLO_ANALYZER;
	}

	@Override
	public RunType getRunType() {
		return RunType.ON_DEMAND;
	}

	
	
}
