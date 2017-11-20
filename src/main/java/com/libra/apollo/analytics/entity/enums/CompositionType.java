package com.libra.apollo.analytics.entity.enums;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
	},
	WHERE{

		@Override
		public Predicate combine(CriteriaBuilder builder, Predicate lhs, Predicate rhs) {
			Predicate where = builder.conjunction();
			
			@SuppressWarnings("rawtypes")
			final CriteriaQuery cq = builder.createQuery();
			cq.where(lhs,rhs);
			return null;
		}
		
	};

	public abstract Predicate combine(CriteriaBuilder builder, Predicate lhs, Predicate rhs);
}
