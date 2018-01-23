package com.libra.apollo.analytics.dto;

import java.util.List;


public class PortfolioScreenerResultDTO {

	private String analyticsType;

	private String runType;

	private Long investmentStyleId;
	
	private String investmentStyleName;

	private List<String> requestedFields;

	private List<String> parameters;

	private List<PortfolioStockResults> results;

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

	public List<PortfolioStockResults> getResults() {
		return results;
	}

	public void setResults(List<PortfolioStockResults> results) {
		this.results = results;
	}

	public class PortfolioStockResults {
		private Long portfolioId;
		private List<Long> stockIds;

		public PortfolioStockResults(Long portfolioId, List<Long> stockIds) {
			this.portfolioId = portfolioId;
			this.stockIds = stockIds;
		}

		public Long getPortfolioId() {
			return portfolioId;
		}

		public List<Long> getStockIds() {
			return stockIds;
		}

		public int getSize() {
			return stockIds != null ? stockIds.size() : 0;
		}

	}
}
