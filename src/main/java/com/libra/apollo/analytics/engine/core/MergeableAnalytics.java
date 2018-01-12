package com.libra.apollo.analytics.engine.core;

public interface MergeableAnalytics {
	boolean isMergeEnabled();
	void merge(Iterable<?> value);
}
