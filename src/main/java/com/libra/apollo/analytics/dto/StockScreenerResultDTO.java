package com.libra.apollo.analytics.dto;

import java.util.List;

public class StockScreenerResultDTO {

	private String analyticsType;

	private String runType;

	private Long investmentStyleId;

	private String investmentStyleName;

	private List<String> requestedFields;

	private List<String> parameters;

	private List<Long> results;

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

	public List<Long> getResults() {
		return results;
	}

	public void setResults(List<Long> results) {
		this.results = results;
	}


}
