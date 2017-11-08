package com.libra.apollo.analytics.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Priority implements Comparable<Priority> {
	private Integer priority;

	@Override
	public int compareTo(Priority o) {
		return this.getPriority().compareTo(o.getPriority());
	}

	public Priority(Integer priority) {
		super();
		this.priority = priority;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Priority() {
		super();
	}

}
