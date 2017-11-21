package com.libra.apollo.analytics.entity.enums;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.repository.specification.StampDateSpecification;

public enum Operand {

	
	WHERE_STAMP_DATE_EQUAL("where"){

		@Override
		public <T> Specification<T> query(final Date date) {
			return StampDateSpecification.stampDateEqual(date);
		}

		
	};
//	GREATER_THAN(">") {
//
//		@Override
//		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
//			return new Specification<T>() {
//				@Override
//				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//					return cb.greaterThan(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
//				}
//
//			};
//		}
//
//	},
//	LESS_THAN("<") {
//
//		@Override
//		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
//			return new Specification<T>() {
//				@Override
//				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//					return cb.lessThan(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
//				}
//
//			};
//		}
//
//	},
//	EQUAL("=") {
//
//		@Override
//		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
//			return new Specification<T>() {
//				@Override
//				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//					return cb.equal(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
//				}
//
//			};
//		}
//
//	},
//	NOT_EQUAL("!=") {
//
//		@Override
//		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
//			return new Specification<T>() {
//				@Override
//				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//					return cb.notEqual(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
//				}
//
//			};
//		}
//
//	},
//	GREATER_THAN_OR_EQUAL(">=") {
//
//		@Override
//		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
//			return new Specification<T>() {
//				@Override
//				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//					return cb.greaterThanOrEqualTo(root.<BigDecimal>get(fieldType.getFieldName()),
//							new BigDecimal(value));
//				}
//
//			};
//		}
//
//	},
//	LESS_THAN_OR_EQUAL("<=") {
//
//		@Override
//		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
//			return new Specification<T>() {
//				@Override
//				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//					return cb.lessThanOrEqualTo(root.<BigDecimal>get(fieldType.getFieldName()), new BigDecimal(value));
//				}
//
//			};
//		}
//
//	};

	private String symbol;

	private Operand(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public abstract <T> Specification<T> query(final Date value);

}
