package com.libra.apollo.analytics.engine.result;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.collect.Lists;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class ScreenerResult implements AnalyticsResult{

	private static final long serialVersionUID = 468624570135610833L;

	private InvestmentStyle investmentStyle;
	
	private List<ValueDataFieldType> requestedFields;
	
	private List<ValueDataFieldType> parameters;

	private Collection<List<?>> results; 
	
	private List<Long> portfolioIds;
	
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	public ScreenerResult(final InvestmentStyle investmentStyle, final List<ValueDataFieldType> requestedFields, final List<ValueDataFieldType> parameters, final Collection<List<?>> results, final List<Long> portfolioIds) {
		super();
		this.investmentStyle = investmentStyle;
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


	@Override
	public List<ValueDataFieldType> getRequestedFields() {
		return requestedFields;
	}

	@Override
	public List<ValueDataFieldType> getParameters() {
		return parameters;
	}

	@Override
	public void addResults(Collection<List<?>> values) {
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
	public Collection<?> getResults() {
		
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
		
		private InvestmentStyle investmentStyle;
		
		private List<ValueDataFieldType> requestedFields;
		
		private List<ValueDataFieldType> parameters;

		private Collection<List<?>> results;
		
		private List<Long> portfolioIds;

		public ScreenerResultBuilder() {
			super();
		} 
		
		public ScreenerResultBuilder setInvestmentStyle(InvestmentStyle investmentStyle) {
			this.investmentStyle = investmentStyle;
			return this;
		}
		
		public ScreenerResultBuilder setRequestedFields(Collection<ValueDataFieldType> requestedFields) {
			this.requestedFields =  Lists.newArrayList(requestedFields);
			return this;
		}
		
		public ScreenerResultBuilder setParameters(Collection<ValueDataFieldType> parameters) {
			this.parameters = Lists.newArrayList(parameters);
			return this;
		}
		
		public ScreenerResultBuilder setResults(Collection<List<?>> results) {
			this.results = results;
			return this;
		}
		
		public ScreenerResultBuilder setPortfolioIds(Collection<Long> portfolioIds) {
			this.portfolioIds = Lists.newArrayList(portfolioIds);
			return this;
		}
		
		public ScreenerResult build() {
			return new ScreenerResult(this.investmentStyle, this.requestedFields, this.parameters, this.results, this.portfolioIds);
		}
	}

	@Override
	public InvestmentStyle getInvestmentStyle() {
		return investmentStyle;
	}
	
}
