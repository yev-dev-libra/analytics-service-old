package com.libra.apollo.analytics.engine.response;

import java.io.Serializable;
import java.util.Collection;

import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public interface AnalyticsResponse extends Serializable {
	
	public AnalyticsType getAnalyticsType();
	public RunType getRunType();
	public Collection<AnalyticsView> getAnalyticsViews();

}
