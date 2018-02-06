package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.libra.apollo.analytics.engine.core.ValueDataField;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;

@Entity
@Table(name = "analytics_field_parameter", schema = "analytics")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorValue(value = "VALUE")
public class FieldParameter implements ValueDataField {

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
	
	

}
