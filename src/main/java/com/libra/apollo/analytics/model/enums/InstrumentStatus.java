package com.libra.apollo.analytics.model.enums;

public enum InstrumentStatus {
	ACTIVE("Active", 1), ACQUIRED("Acquired", 2), DELISTED("Delisted", 3), TICKER_CHANGED("Ticker Changed", 4), SUSPENDED("Suspended", 5);
	
	InstrumentStatus(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	String name;
	int value;
	
	public String getName(){
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString(){
		return name;
	}
}