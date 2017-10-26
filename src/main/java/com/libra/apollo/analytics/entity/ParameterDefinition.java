package com.libra.apollo.analytics.entity;

public enum ParameterDefinition {

	FV("fairValue","Fair Value"), 
	IV("intrinsicValue","Intrinsic Value"),
	FV_1M("",""),
	IV_1M("",""),
	STAR_RATING("starRating",""),
	INTRINSIC_VALUE_PCT("intrinsic_value_pct","Value Indicator")
	
	;
	
	private final String fieldName;
	private final String name;
	
	private ParameterDefinition(String fieldName, String name) {
		this.fieldName = fieldName;
		this.name= name;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getName() {
		return name;
	}
	
	
}
