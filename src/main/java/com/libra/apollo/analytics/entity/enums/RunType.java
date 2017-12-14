package com.libra.apollo.analytics.entity.enums;

public enum RunType {
	MANUAL("manual"), SCHEDULED("scheduled");
	private final String name;

	private RunType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static RunType getRunType(String name) {
		for (RunType type : RunType.values())
			if (name.equals(type.getName()))
				return type;
		return null;
	}

}
