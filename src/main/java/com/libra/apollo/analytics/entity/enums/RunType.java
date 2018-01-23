package com.libra.apollo.analytics.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RunType {
	MANUAL("Manual"),
	SCHEDULED("Scheduled"),
	ON_DEMAND("On demand");
	
	private final String name;

	private RunType(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public static RunType getRunType(String name) {
		for (RunType type : RunType.values())
			if (name.equals(type.getName()))
				return type;
		return null;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
