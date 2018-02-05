package com.libra.apollo.analytics.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AnalyticsType implements Serializable {

	APOLLO_SCREENER("Apollo Screener");

	private final String name;

	private AnalyticsType(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static AnalyticsType getAnalyticsType(String name) {
		for (AnalyticsType type : AnalyticsType.values())
			if (name.equals(type.getName()))
				return type;
		return null;
	}
	
	@Override
	public String toString() {
		return name;
	}
	

}
