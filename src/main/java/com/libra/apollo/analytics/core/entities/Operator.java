package com.libra.apollo.analytics.core.entities;

public enum Operator {

	GREATER_THAN(">"), 
	LESS_THAN("<"), 
	EQUAL("="), 
	NOT_EQUAL("!="),
	GREATER_THAN_OR_EQUAL(">="), 
	LESS_THAN_OR_EQUAL("<=");
	
	private String symbol;
	
	private Operator(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {return symbol;}
	
}
