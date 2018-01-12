package com.libra.apollo.analytics.engine.result;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.libra.apollo.analytics.engine.core.MergeableAnalytics;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class ScreenerResult implements AnalyticsResult, MergeableAnalytics {

	/**
	 * 
	 */
	private static final long serialVersionUID = 468624570135610833L;

	private final ValueDataFieldType[] requestedFields;
	
	private final ValueDataFieldType[] parameters;

	private List<Iterable<?>> results; 
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public ScreenerResult(final ValueDataFieldType[] requestedFields, ValueDataFieldType[] parameters) {
		super();
		this.requestedFields = requestedFields;
		this.parameters = parameters;
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
		return Arrays.asList(requestedFields);
	}

	@Override
	public List<ValueDataFieldType> getParameters() {
		return Arrays.asList(parameters);
	}

	@Override
	public void merge(Iterable<?> value) {
		readWriteLock.writeLock().lock();

		try {
			results.add(value);
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	@Override
	public List<Iterable<?>> getResults() {
		
		try {
			readWriteLock.readLock().lock();
			return results;
			
		} finally {
			readWriteLock.readLock().unlock();
		}
	}

}
