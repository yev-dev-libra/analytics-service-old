package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.specification.ValueParameter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "analytics_parameter", schema="analytics")
@Table(name = "analytics_parameter")
public abstract class QueryParameter implements ValueParameter {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	protected Long id;
	
	@Column(name = "composition_type", nullable = true)
	@Enumerated(EnumType.STRING)
	private CompositionType compositionType;
	
	@Column(name = "data_field_type", nullable = true)
	@Enumerated(EnumType.STRING)
	private ValueDataFieldType fieldType;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public CompositionType getCompositionType() {
		return compositionType;
	}

	public void setCompositionType(CompositionType compositionType) {
		this.compositionType = compositionType;
	}

	@Override
	public ValueDataFieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(ValueDataFieldType fieldType) {
		this.fieldType = fieldType;
	}


}
