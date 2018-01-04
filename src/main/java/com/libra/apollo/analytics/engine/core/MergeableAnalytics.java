package com.libra.apollo.analytics.engine.core;

import java.io.Serializable;

public interface MergeableAnalytics {
	boolean isMergeEnabled();
	void merge(Iterable<? extends Serializable> value);
}
