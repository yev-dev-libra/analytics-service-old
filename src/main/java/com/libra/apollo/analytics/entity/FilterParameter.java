package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.libra.apollo.analytics.entity.enums.CompositionType;
import com.libra.apollo.analytics.entity.enums.InstrumentDataFieldType;
import com.libra.apollo.analytics.entity.enums.Operation;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "FILTER")
public class FilterParameter extends Parameter {
	
	@Column(name="data_field_type")
	@Enumerated(EnumType.STRING)
	private CompositionType compositionType;
	
	@Column(name="data_field_type")
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
	
	private String compositionOperator;
	private String field;
	private Operation operation;
	private String value;
	
}
