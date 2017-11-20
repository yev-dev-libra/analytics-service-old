package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.libra.apollo.analytics.entity.enums.CompositionType;

/*
 * 
 */
@Entity
@DiscriminatorValue(value = "FILTER_PARAMETER")
public class FilterParameter extends Property {

	private static final long serialVersionUID = -7150480944235853200L;

	@Column(name = "composition_type")
	@Enumerated(EnumType.STRING)
	private CompositionType compositionType;

	public CompositionType getCompositionType() {
		return compositionType;
	}

	public void setCompositionType(CompositionType compositionType) {
		this.compositionType = compositionType;
	}

}
