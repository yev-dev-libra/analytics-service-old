package com.libra.apollo.analytics.core.entities;

import lombok.Data;

public @Data class Criteria {
	private String factor;
	private double value;
	private Operator operator;
}
