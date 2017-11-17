package com.libra.apollo.analytics.repository.specification;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.entity.InstrumentDataFieldType;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

public class LibraStockIndicatorSpecification<T extends LibraStockIndicator> {

	public Specification<T> stampDateEquals(final Date date) {
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Date>get("stampDate"), date);
			}

		};

	}

	public Specification<T> stockIdEquals(final Long stockId) {
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Long>get("stockId"), stockId);
			}

		};

	}
	
	public Specification<T> fieldEqualsTo(final InstrumentDataFieldType fieldType, double value ){
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<BigDecimal>get(fieldType.getFieldName()), value);
			}

		};
	}
	
	public Specification<T> fieldGreaterThan(final InstrumentDataFieldType fieldType, double value ){
		return new Specification<T>() {
			
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThan(root.<BigDecimal>get(fieldType.getFieldName()), BigDecimal.valueOf(value));
			}
			
		};
	}
	public Specification<T> fieldGreaterThanOrEqualTo(final InstrumentDataFieldType fieldType, double value ){
		return new Specification<T>() {
			
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThanOrEqualTo(root.<BigDecimal>get(fieldType.getFieldName()), BigDecimal.valueOf(value));
			}
			
		};
	}

}
