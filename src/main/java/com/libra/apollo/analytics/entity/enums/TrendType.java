package com.libra.apollo.analytics.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum TrendType {

	
	NONE(0), UP(1), DOWN(-1);

	private final int value;

	private static final Map<Integer, TrendType> lookup = new HashMap<Integer, TrendType>();
	
	static {

		for (TrendType trend : TrendType.values())

			lookup.put(trend.getValue(), trend);

	}
	

	private TrendType(int value) {

		this.value = value;

	}

	public int getValue() {

		return value;

	}

	public static TrendType getTrend(Integer value) {

		return lookup.get(value);

	}
}
