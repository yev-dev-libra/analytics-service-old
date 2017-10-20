package com.libra.apollo.analytics.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Priority {
	private Integer priority;
}
