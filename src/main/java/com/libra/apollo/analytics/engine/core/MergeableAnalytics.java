package com.libra.apollo.analytics.engine.core;

import java.util.Collection;
import java.util.List;

public interface MergeableAnalytics {
	boolean isMergeEnabled();
	void merge(Collection<List<?>> values);
}
