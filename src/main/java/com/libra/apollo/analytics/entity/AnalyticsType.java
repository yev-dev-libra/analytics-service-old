package com.libra.apollo.analytics.entity;


public enum AnalyticsType {

	APOLLO_SCREENER("");
	
	private final String desc;
	
	private AnalyticsType(String desc) {
		this.desc = desc;
	}

	public String getDescription() {
		return desc;
	}
	
	
	

}
