package com.libra.apollo.analytics.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

@SuppressWarnings("serial")
public class ApolloSpecificationsAdapter<T> {

	private final Specifications<T> spec;
	
	public ApolloSpecificationsAdapter(Specification<T> initialSpec) {
		spec = Specifications.where(initialSpec);
	}

	
	public Specifications<T> getSpec() {
		return spec;
	}
	
	
	enum CompositionType {

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

		abstract Predicate combine(CriteriaBuilder builder, Predicate lhs, Predicate rhs);
	}
	

	
}
