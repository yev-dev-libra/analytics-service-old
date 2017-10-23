package com.libra.apollo.analytics.entity;

public enum Operation {

	GREATER_THAN(">"), 
	LESS_THAN("<"), 
	EQUAL("="), 
	NOT_EQUAL("!="),
	GREATER_THAN_OR_EQUAL(">="), 
	LESS_THAN_OR_EQUAL("<="), 
	SORT_ASC(""),
	SORT_DESC(""),
	SORT_TOP(""),
	SORT_BOTTOM(""),
	SORT_TOP_BOTTOM(""),
	;
	
	private String symbol;
	
	private Operation(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {return symbol;}
	
}
