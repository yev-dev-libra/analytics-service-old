package com.libra.apollo.analytics.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "OPERATION")
@Data
@EqualsAndHashCode(callSuper=true)
public class OperationCondition extends Condition {

	@Enumerated(EnumType.STRING)
	private Operation operator;
}
