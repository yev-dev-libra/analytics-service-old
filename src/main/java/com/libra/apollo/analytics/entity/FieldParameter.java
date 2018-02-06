package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.libra.apollo.analytics.engine.core.ValueDataField;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;

@Entity
@Table(name = "analytics_field_parameter", schema = "analytics")
public class FieldParameter implements ValueDataField, Comparable<FieldParameter>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4760084034378078297L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	protected Long id;
	
	@Column(name = "data_field_type", nullable = true)
	@Enumerated(EnumType.STRING)
	private ValueDataFieldType fieldType;
	
	public ValueDataFieldType getFieldType() {
		return fieldType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public void setFieldType(ValueDataFieldType fieldType) {
		this.fieldType = fieldType;
	}

	@Override
	public int compareTo(FieldParameter o) {
		return this.getId().compareTo(o.getId());
	}
	
}