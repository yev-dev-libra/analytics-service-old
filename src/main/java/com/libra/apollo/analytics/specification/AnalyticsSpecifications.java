package com.libra.apollo.analytics.specification;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

public class AnalyticsSpecifications<T> implements Specification<T>, Serializable {

	private static final long serialVersionUID = 1L;

	private AnalyticsSpecification<T> spec;

	public Specification<T> getSpec() {
		return spec;
	}
	
	public AnalyticsSpecifications(Specification<T> spec) {
		this.spec = new AnalyticsSpecification<T>(spec);
	}

	public void and(Specification<T> other) {
		spec = new AnalyticsSpecification<T>(new ComposedSpecification<T>(spec, other, CompositionType.AND));
	}

	public void or(Specification<T> other) {
		spec = new AnalyticsSpecification<T>(new ComposedSpecification<T>(spec, other, CompositionType.OR));
	}

	public void not(Specification<T> spec) {
		spec =  new AnalyticsSpecification<T>(new NegatedSpecification<T>(spec));
	}

	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return spec == null ? null : spec.toPredicate(root, query, builder);
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

	@SuppressWarnings("serial")
	private static class AnalyticsSpecification<T> implements Specification<T>, Serializable {

		private final Specification<T> spec;
		
		public AnalyticsSpecification(Specification<T> spec) {
			super();
			this.spec = spec;
		}
		
		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
			return spec == null ? null : spec.toPredicate(root, query, builder);
		}
	}
	
	@SuppressWarnings("serial")
	private static class WhereClauseSpecification<T> implements Specification<T>, Serializable{

		private final Specification<T> spec;
		
		public WhereClauseSpecification(Specification<T> spec) {
			super();
			this.spec = spec;
		}

		@Override
		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
			Predicate p = spec == null ? null : builder.not(spec.toPredicate(root, query, builder));
			return query.where(p).getRestriction();
		}

		
	}
	
	@SuppressWarnings("serial")
	private static class NegatedSpecification<T> implements Specification<T>, Serializable {

		private final Specification<T> spec;

		public NegatedSpecification(Specification<T> spec) {
			this.spec = spec;
		}

		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
			return spec == null ? null : builder.not(spec.toPredicate(root, query, builder));
		}
	}

	@SuppressWarnings("serial")
	private static class ComposedSpecification<T> implements Specification<T>, Serializable {

		private final Specification<T> lhs;
		private final Specification<T> rhs;
		private final CompositionType compositionType;

		private ComposedSpecification(Specification<T> lhs, Specification<T> rhs, CompositionType compositionType) {

			Assert.notNull(compositionType, "CompositionType must not be null!");

			this.lhs = lhs;
			this.rhs = rhs;
			this.compositionType = compositionType;
		}

		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

			Predicate otherPredicate = rhs == null ? null : rhs.toPredicate(root, query, builder);
			Predicate thisPredicate = lhs == null ? null : lhs.toPredicate(root, query, builder);

			return thisPredicate == null ? otherPredicate
					: otherPredicate == null ? thisPredicate
							: this.compositionType.combine(builder, thisPredicate, otherPredicate);
		}
	}
}