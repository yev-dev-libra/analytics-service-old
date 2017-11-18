package com.libra.apollo.analytics.entity.enums;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public enum CompositionType {

	AND {
		@Override
		public Predicate combine(CriteriaBuilder builder, Predicate lhs, Predicate rhs) {
			return builder.and(lhs, rhs);
		}
	},

	OR {
		@Override
		public Predicate combine(CriteriaBuilder builder, Predicate lhs, Predicate rhs) {
			return builder.or(lhs, rhs);
		}
	};

	public abstract Predicate combine(CriteriaBuilder builder, Predicate lhs, Predicate rhs);
}
