package com.libra.apollo.analytics.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class SortDirection {

	public enum Direction {
		ASC(Operation.SORT_ASC), 
		DESC(Operation.SORT_DESC), 
		TOP_DECILE(Operation.SORT_TOP), 
		BOTTOM_DECILE(Operation.SORT_BOTTOM), 
		TOP_BOTTOM_DECILE(Operation.SORT_TOP_BOTTOM);
		
		private final Operation operation;

		private Direction(Operation operation) {
			this.operation = operation;
		}

		public Operation getOperation() {
			return operation;
		}
		
	}

	private Direction direction;

}
