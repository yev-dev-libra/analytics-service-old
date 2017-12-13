package com.libra.apollo.analytics.entity.enums;

public enum AnalyticsType {

	APOLLO_SCREENER("Apollo Screener");

	private final String name;

	private AnalyticsType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static AnalyticsType getAnalyticsType(String name) {
		for (AnalyticsType type : AnalyticsType.values())
			if (name.equals(type.getName()))
				return type;
		return null;
	}

}
