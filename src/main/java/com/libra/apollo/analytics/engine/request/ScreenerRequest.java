package com.libra.apollo.analytics.engine.request;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class ScreenerRequest implements AnalyticsRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1863827676275650547L;

	private final List<Long> portfolioIds;
	private final Long investmentStyleId; //TODO: this should be investment style populated by intercepter
	
	
	
	public ScreenerRequest(Long investmentStyleId, List<Long> portfolioIds ) {
		super();
		this.portfolioIds = portfolioIds;
		this.investmentStyleId = investmentStyleId;
	}

	
	public Collection<Long> getPortfolioIds() {
		return portfolioIds;
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
	public Operation getOperation() {
		return Operation.SCREEN_FOR_PORTFOLIO;
	}

	public Date getRunDate() {
		return null;
	}


	public Long getInvestmentStyleId() {
		return investmentStyleId;
	}

	public static ScreenerRequest of(Long investmentStyleId, List<Long> portfolioIds ) {
		return new ScreenerRequest(investmentStyleId, portfolioIds);
	}
	
}
