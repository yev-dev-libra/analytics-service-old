package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "PARAMETER")
public class ParameterCondition extends Condition {

	@Enumerated(EnumType.STRING)
	private InstrumentDataFieldType dataFieldType;
	
	
	@Column(name="parameter", nullable=true)
	private double parameter;


	public InstrumentDataFieldType getDataFieldType() {
		return dataFieldType;
	}


	public void setDataFieldType(InstrumentDataFieldType dataFieldType) {
		this.dataFieldType = dataFieldType;
	}


	public double getParameter() {
		return parameter;
	}


	public void setParameter(double parameter) {
		this.parameter = parameter;
	}
	
	
}
