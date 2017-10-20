package com.libra.apollo.analytics.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class SortDirection {

	public enum Direction { ASC, DESC;}
	
	private Direction direction;
}
