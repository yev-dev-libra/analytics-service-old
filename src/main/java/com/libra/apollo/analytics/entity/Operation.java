package com.libra.apollo.analytics.entity;

public enum Operation {
	
	AND(""),
	OR(""),
	GREATER_THAN(">"), 
	LESS_THAN("<"), 
	EQUAL("="), 
	NOT_EQUAL("!="),
	GREATER_THAN_OR_EQUAL(">="), 
	LESS_THAN_OR_EQUAL("<="), 
	;
	
	private String symbol;
	
	private Operation(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {return symbol;}
	
}
