package com.libra.apollo.analytics.dto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.RunType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PortfolioScreenerResultDTO {
	
	@ApiModelProperty(notes = "Requested list of portfolio ids")
	private List<Long> portfolioIds;
	
	@ApiModelProperty(notes = "Type of analytics")
	private AnalyticsType analyticsType;

	@ApiModelProperty(notes = "Requested run type")
	private RunType runType;

	@ApiModelProperty(notes = "Requested Investment Style unique identifier")
	private Long investmentStyleId;
	
	@ApiModelProperty(notes = "Requested Investment Style name")
	private String investmentStyleName;

	@ApiModelProperty(notes = "List of fields to display")
	private List<ValueDataFieldType> requestedFields;
	
	@ApiModelProperty(notes = "List of fields to screen by")
	private List<ValueDataFieldType> parameters;

	@ApiModelProperty(notes = "List of fields to screen by")
	private Map<Long, List<PortfolioStockValues>> portfolios;
	
	public List<Long> getPortfolioIds() {
		return portfolioIds;
	}

	public void setPortfolioIds(List<Long> portfolioIds) {
		this.portfolioIds = portfolioIds;
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


	public AnalyticsType getAnalyticsType() {
		return analyticsType;
	}

	public void setAnalyticsType(AnalyticsType analyticsType) {
		this.analyticsType = analyticsType;
	}

	public RunType getRunType() {
		return runType;
	}

	public void setRunType(RunType runType) {
		this.runType = runType;
	}

	public List<ValueDataFieldType> getRequestedFields() {
		return requestedFields;
	}

	public void setRequestedFields(List<ValueDataFieldType> requestedFields) {
		this.requestedFields = requestedFields;
	}

	public List<ValueDataFieldType> getParameters() {
		return parameters;
	}

	public void setParameters(List<ValueDataFieldType> parameters) {
		this.parameters = parameters;
	}


	public Map<Long, List<PortfolioStockValues>> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(Map<Long, List<PortfolioStockValues>> portfolios) {
		this.portfolios = portfolios;
	}



	public static class PortfolioStockValues {
		private final Long stockId;
		private final EnumMap<ValueDataFieldType,?> values;

		public PortfolioStockValues(final Long stockId, final EnumMap<ValueDataFieldType,?> values) {
			this.stockId = stockId;
			this.values = values;
		}

		public Long getStockId() {
			return stockId;
		}

		public EnumMap<ValueDataFieldType,?> getValues() {
			return values;
		}

	}
}
