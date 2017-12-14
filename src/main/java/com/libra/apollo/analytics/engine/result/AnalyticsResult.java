package com.libra.apollo.analytics.engine.result;

import java.io.Serializable;

import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public interface AnalyticsResult extends Serializable {
	
	public AnalyticsType getAnalyticsType();
	public RunType getRunType();
	

}
