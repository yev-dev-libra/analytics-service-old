package com.libra.apollo.analytics.entity;

public enum AnalyticsSearchFieldApi {

	NAME("name"),
	RUN_TYPE("runType"),
	ANALYTICS_TYPE("analyticsType");

	private final String name;

	private AnalyticsSearchFieldApi(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static AnalyticsSearchFieldApi getAnalyticsSearchFieldApi(String name) {
		for (AnalyticsSearchFieldApi type : AnalyticsSearchFieldApi.values())
			if (name.equals(type.getName()))
				return type;
		return null;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
