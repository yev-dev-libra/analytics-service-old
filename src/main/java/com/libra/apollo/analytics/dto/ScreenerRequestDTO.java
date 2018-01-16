package com.libra.apollo.analytics.dto;

import java.util.List;

public class ScreenerRequestDTO {

	private String type;
	private String runType;
	private String operation;
	private List<Long> portfolioIds;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRunType() {
		return runType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public List<Long> getPortfolioIds() {
		return portfolioIds;
	}

	public void setPortfolioIds(List<Long> portfolioIds) {
		this.portfolioIds = portfolioIds;
	}

}
