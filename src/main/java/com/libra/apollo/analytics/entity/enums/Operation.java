package com.libra.apollo.analytics.entity.enums;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public enum Operation {

	GREATER_THAN(">") {

		@Override
		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
			return new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.greaterThan(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
				}

			};
		}

	},
	LESS_THAN("<") {

		@Override
		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
			return new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.lessThan(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
				}

			};
		}

	},
	EQUAL("=") {

		@Override
		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
			return new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
				}

			};
		}

	},
	NOT_EQUAL("!=") {

		@Override
		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
			return new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.notEqual(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
				}

			};
		}

	},
	GREATER_THAN_OR_EQUAL(">=") {

		@Override
		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
			return new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.greaterThanOrEqualTo(root.<BigDecimal>get(fieldType.getFieldName()),
							new BigDecimal(value));
				}

			};
		}

	},
	LESS_THAN_OR_EQUAL("<=") {

		@Override
		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
			return new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.lessThanOrEqualTo(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
				}

			};
		}

	};

	private String symbol;

	private Operation(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public abstract <T> Specification<T> query(final InstrumentDataFieldType fieldType, String value);

}
