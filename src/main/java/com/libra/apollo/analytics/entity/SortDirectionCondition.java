package com.libra.apollo.analytics.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "SORT_DIR")
@Data
@EqualsAndHashCode(callSuper=true)
public class SortDirectionCondition extends Condition {

	@Enumerated(EnumType.STRING)
	private SortDirection sortDirection;
}
