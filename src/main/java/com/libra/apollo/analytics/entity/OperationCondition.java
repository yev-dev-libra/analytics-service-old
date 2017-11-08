package com.libra.apollo.analytics.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "OPERATION")
public class OperationCondition extends Condition {

	@Enumerated(EnumType.STRING)
	private Operation operator;

	public Operation getOperator() {
		return operator;
	}

	public void setOperator(Operation operator) {
		this.operator = operator;
	}
	
	
}
