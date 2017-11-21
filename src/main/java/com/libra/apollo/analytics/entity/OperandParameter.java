package com.libra.apollo.analytics.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.libra.apollo.analytics.entity.enums.Operand;

/*
 * E.g. WHERE_STAMP_DATE_EQUAL stampDate
 */
@Entity
@DiscriminatorValue(value = "OPERAND_PARAMETER")
public class OperandParameter extends Property {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3113127181216187044L;
	@Enumerated(EnumType.STRING)
	private Operand operand;
	
	
	public Operand getOperand() {
		return operand;
	}
	public void setOperand(Operand operand) {
		this.operand = operand;
	}
	@Override
	public String toString() {
		return "OperandParameter [operand=" + operand + "]";
	}
	
	

	
}
