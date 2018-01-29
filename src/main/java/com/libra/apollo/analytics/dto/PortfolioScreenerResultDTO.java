package com.libra.apollo.analytics.dto;

import java.util.List;
import java.util.Map;


public class PortfolioScreenerResultDTO {

	private List<Long> portfolioIds;

	private String analyticsType;

	private String runType;

	private Long investmentStyleId;
	
	private String investmentStyleName;

	private List<String> requestedFields;

	private List<String> parameters;

	
	private Map<Long, List<PortfolioStockResults>> results;
	
	public List<Long> getPortfolioIds() {
		return portfolioIds;
	}

	public void setPortfolioIds(List<Long> portfolioIds) {
		this.portfolioIds = portfolioIds;
	}

	public String getAnalyticsType() {
		return analyticsType;
	}

	public void setAnalyticsType(String analyticsType) {
		this.analyticsType = analyticsType;
	}

	public String getRunType() {
		return runType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

	public Long getInvestmentStyleId() {
		return investmentStyleId;
	}

	public void setInvestmentStyleId(Long investmentStyleId) {
		this.investmentStyleId = investmentStyleId;
	}

	public String getInvestmentStyleName() {
		return investmentStyleName;
	}

	public void setInvestmentStyleName(String investmentStyleName) {
		this.investmentStyleName = investmentStyleName;
	}

	public List<String> getRequestedFields() {
		return requestedFields;
	}

	public void setRequestedFields(List<String> requestedFields) {
		this.requestedFields = requestedFields;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}


	public Map<Long, List<PortfolioStockResults>> getResults() {
		return results;
	}

	public void setResults(Map<Long, List<PortfolioStockResults>> results) {
		this.results = results;
	}




	public class PortfolioStockResults {
		private final Long stockId;
		private final Map<String,Object> values;

		public PortfolioStockResults(final Long stockId, final Map<String,Object> values) {
			this.stockId = stockId;
			this.values = values;
		}

		public Long getStockId() {
			return stockId;
		}

		public Map<String, Object> getValues() {
			return values;
		}

	}
}
