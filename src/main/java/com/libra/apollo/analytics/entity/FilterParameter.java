package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.libra.apollo.analytics.entity.enums.CompositionType;
import com.libra.apollo.analytics.entity.enums.InstrumentDataFieldType;

/*
 * 
 */
@Entity
@DiscriminatorValue(value = "FILTER_PARAMETER")
public class FilterParameter extends Property {

	private static final long serialVersionUID = -7150480944235853200L;

	@Column(name = "data_field_type", nullable = true)
	@Enumerated(EnumType.STRING)
	private InstrumentDataFieldType dataFieldType;
	
	@Column(name = "composition_type", nullable = true)
	@Enumerated(EnumType.STRING)
	private CompositionType compositionType;

	
	public InstrumentDataFieldType getDataFieldType() {
		return dataFieldType;
	}

	public void setDataFieldType(InstrumentDataFieldType dataFieldType) {
		this.dataFieldType = dataFieldType;
	}

	public CompositionType getCompositionType() {
		return compositionType;
	}

	public void setCompositionType(CompositionType compositionType) {
		this.compositionType = compositionType;
	}

	@Override
	public String toString() {
		return "FilterParameter [dataFieldType=" + dataFieldType + ", compositionType=" + compositionType + "]";
	}
	
	

}
