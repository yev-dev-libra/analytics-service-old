package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.libra.apollo.analytics.entity.enums.ConditionallyRequired;

@Entity
@DiscriminatorValue(value="RETURN_VALUE_PARAMETER")
public class ReturnValueParameter extends Property {

	private static final long serialVersionUID = 4049107383053106790L;

	@Column(name="conditional")
	@Enumerated(EnumType.STRING)
	private ConditionallyRequired conditional;

	public ConditionallyRequired getConditional() {
		return conditional;
	}

	public void setConditional(ConditionallyRequired conditional) {
		this.conditional = conditional;
	}
	
	
}
