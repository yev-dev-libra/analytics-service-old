package com.libra.apollo.analytics.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Priority implements Comparable<Priority> {
	private Integer priority;

	@Override
	public int compareTo(Priority o) {
		return this.getPriority().compareTo(o.getPriority()) ; 
	}

}
