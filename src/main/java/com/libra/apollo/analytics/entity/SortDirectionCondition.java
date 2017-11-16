package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "SORT_DIR")
public class SortDirectionCondition extends Condition {

	@Column(name="sort_direction")
	@Enumerated(EnumType.STRING)
	private SortDirection sortDirection;

	public SortDirection getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}
	
	
}
