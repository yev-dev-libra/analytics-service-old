package com.libra.apollo.analytics.engine.result;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.libra.apollo.analytics.engine.core.MergeableAnalytics;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class ScreenerResult implements AnalyticsResult, MergeableAnalytics {

	private static final long serialVersionUID = 468624570135610833L;

	
	private List<ValueDataFieldType> requestedFields;
	
	private List<ValueDataFieldType> parameters;

	private List<List<?>> results; 
	
	private List<Long> portfolioIds;
	
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	public ScreenerResult(List<ValueDataFieldType> requestedFields, List<ValueDataFieldType> parameters, List<List<?>> results, List<Long> portfolioIds) {
		super();
		this.requestedFields = requestedFields;
		this.parameters = parameters;
		
		if(results == null) {
			this.results = new LinkedList<>();
		}
		else
			this.results = results;
		
		this.portfolioIds = portfolioIds;
	}

	@Override
	public AnalyticsType getAnalyticsType() {
		return AnalyticsType.APOLLO_SCREENER;
	}

	@Override
	public RunType getRunType() {
		return RunType.MANUAL;
	}

	@JsonIgnore
	@Override
	public boolean isMergeEnabled() {
		return Boolean.TRUE;
	}

	@Override
	public List<ValueDataFieldType> getRequestedFields() {
		return requestedFields;
	}

	@Override
	public List<ValueDataFieldType> getParameters() {
		return parameters;
	}

	@Override
	public void merge(Collection<List<?>> values) {
		readWriteLock.writeLock().lock();

		try {
			for(List<?> list : values) {
				results.add(list);
			}
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	@Override
	public List<?> getResults() {
		
		try {
			readWriteLock.readLock().lock();
			return results;
			
		} finally {
			readWriteLock.readLock().unlock();
		}
	}

	public void setRequestedFields(List<ValueDataFieldType> requestedFields) {
		this.requestedFields = requestedFields;
	}

	public void setParameters(List<ValueDataFieldType> parameters) {
		this.parameters = parameters;
	}

	
	
	public List<Long> getPortfolioIds() {
		return portfolioIds;
	}

	public void setPortfolioIds(List<Long> portfolioIds) {
		this.portfolioIds = portfolioIds;
	}



	public static class ScreenerResultBuilder{
		
		private List<ValueDataFieldType> requestedFields;
		
		private List<ValueDataFieldType> parameters;

		private List<List<?>> results;
		
		private List<Long> portfolioIds;

		public ScreenerResultBuilder() {
			super();
		} 
		
		public ScreenerResultBuilder setRequestedFields(Collection<ValueDataFieldType> requestedFields) {
			this.requestedFields =  Lists.newArrayList(requestedFields);
			return this;
		}
		
		public ScreenerResultBuilder setParameters(Collection<ValueDataFieldType> parameters) {
			this.parameters = Lists.newArrayList(parameters);
			return this;
		}
		
		public ScreenerResultBuilder setResults(List<List<?>> results) {
			this.results = results;
			return this;
		}
		
		public ScreenerResultBuilder setPortfolioIds(Collection<Long> portfolioIds) {
			this.portfolioIds = Lists.newArrayList(portfolioIds);
			return this;
		}
		
		public ScreenerResult build() {
			return new ScreenerResult(this.requestedFields, this.parameters, this.results, this.portfolioIds);
		}
		
		
	}
	
}
