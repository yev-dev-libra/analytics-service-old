package com.libra.apollo.analytics.engine.result;

import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.collect.Lists;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.RunType;

public class ScreenerResult implements AnalyticsResult{

	private static final long serialVersionUID = 468624570135610833L;

	private InvestmentStyle investmentStyle;
	
	private List<ValueDataFieldType> requestedFields;
	
	private List<ValueDataFieldType> parameters;

	private List<EnumMap<ValueDataFieldType,?>> results; 
	
	private List<Long> portfolioIds;
	
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	public ScreenerResult(final InvestmentStyle investmentStyle, final List<ValueDataFieldType> requestedFields, final List<ValueDataFieldType> parameters, final List<EnumMap<ValueDataFieldType,?>> results, final List<Long> portfolioIds) {
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
	public void addValueResults(List<EnumMap<ValueDataFieldType,?>> values) {
		readWriteLock.writeLock().lock();

		try {
			for(EnumMap<ValueDataFieldType,?> list : values) {
				results.add(list);
			}
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	@Override
	public List<EnumMap<ValueDataFieldType,?>> getResultValues() {
		
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

		private List<EnumMap<ValueDataFieldType,?>> results;
		
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
		
		public ScreenerResultBuilder setResults(List<EnumMap<ValueDataFieldType,?>>results) {
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
