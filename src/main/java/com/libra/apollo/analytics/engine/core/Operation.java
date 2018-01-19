package com.libra.apollo.analytics.engine.core;

public enum Operation {

	MODEL("model"), PERSIST("persist"), VALIDATE("validate"), SCREEN_FOR_PORTFOLIO("screen for portfolio"), SCREEN_FOR_STOCK("screen for stock");

	private String name;

	Operation(String name) {
		this.name = name;
	}

	public static Operation getOperation(String name) {
		for (Operation operation : Operation.values())
			if (name.equals(operation.getName()))
				return operation;
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
	