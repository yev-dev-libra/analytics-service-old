package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "PARAMETER")
@Data
@EqualsAndHashCode(callSuper=true)
public class ParameterCondition extends Condition {

	@Enumerated(EnumType.STRING)
	private ParameterDefinition criteriaDefinition;
	
	
	@Column(name="parameter", nullable=false)
	private double parameter;
}
