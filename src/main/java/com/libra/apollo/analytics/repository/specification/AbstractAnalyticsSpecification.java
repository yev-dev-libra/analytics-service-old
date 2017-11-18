package com.libra.apollo.analytics.repository.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class AbstractAnalyticsSpecification<T> implements AnalyticsSpecification<T> {

	public Specification<T> stampDateEqual(final Date date) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Date>get("stampDate"), date);
			}

		};

	}
	public Specification<T> stampDateGreaterThanOrEqual(final Date date) {
		return new Specification<T>() {
			
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThanOrEqualTo(root.<Date>get("stampDate"), date);
			}
			
		};
		
	}
	public Specification<T> stampDateLessThanOrEqual(final Date date) {
		return new Specification<T>() {
			
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.lessThanOrEqualTo(root.<Date>get("stampDate"), date);
			}
			
		};
		
	}
}
