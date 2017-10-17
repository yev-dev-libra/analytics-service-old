package com.libra.apollo.analytics.core.entities;

import lombok.Data;

public @Data class InvestmentCriteria {
	private String factor;
	private double value;
	private Operator operator;
}
