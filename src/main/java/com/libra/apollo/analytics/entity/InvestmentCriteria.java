package com.libra.apollo.analytics.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="insvestment_criteria", schema="analytics")
public @Data class InvestmentCriteria {
	private String factor;
	private double value;
	private Operation operator;
}
