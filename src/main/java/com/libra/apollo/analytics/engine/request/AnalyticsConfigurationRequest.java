package com.libra.apollo.analytics.engine.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.RunType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyticsConfigurationRequest implements AnalyticsRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -580904360065898830L;

	@JsonProperty("analytics_type")
	private String analyticsType;
	
	@JsonProperty("run_type")
	private String runType;
	
	@JsonProperty("operation")
	private String operation;
	
	@JsonIgnore
	public AnalyticsConfigurationRequest(String analyticsType, String runType, String operation) {
		super();
		this.analyticsType = analyticsType;
		this.runType = runType;
		this.operation = operation;
	}

	@Override
	public AnalyticsType getAnalyticsType() {
		return AnalyticsType.getAnalyticsType(analyticsType);
	}

	@Override
	public RunType getRunType() {
		return RunType.getRunType(runType);
	}

	@Override
	public Operation getOperation() {
		return Operation.getOperation(operation);
	}

	public void setAnalyticsType(String analyticsType) {
		this.analyticsType = analyticsType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Builder{
		
		private String analyticsType;
		private String runType;
		private String operation;
		
		public Builder() {
			
		}
		
		public Builder analyticsType(String analyticsType) {
			this.analyticsType = analyticsType;
			return this;
		}
		
		public Builder runType(String runType) {
			this.runType = runType;
			return this;
		}
		
		public Builder operation(String operation) {
			this.operation = operation;
			return this;
		}
		
		public AnalyticsConfigurationRequest build() {
			return new AnalyticsConfigurationRequest(analyticsType,runType,operation);
		}
	}

}
