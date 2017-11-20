package com.libra.apollo.analytics.entity.enums;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.libra.apollo.analytics.repository.specification.CommonAnalyticsSpecification;
import com.libra.apollo.analytics.utils.Properties;

public enum Operand {

	
	WHERE_STAMP_DATE_EQUAL("where"){

		@Override
		public <T> Specification<T> query(InstrumentDataFieldType fieldType, String value) {
			//TDO: Allow a default value
			SimpleDateFormat formatter = new SimpleDateFormat(Properties.DEFAULT_STAMP_DATE_FORMAT);
			
			//TODO exception handling
			Date stampDate = null;
			try {
				stampDate = formatter.parse(value);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			final Specification<T> stampDateEqualsSpec = new CommonAnalyticsSpecification<T>().stampDateEqual(stampDate);
			
			return Specifications.where(stampDateEqualsSpec);
		}
		
	},
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

	private Operand(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public abstract <T> Specification<T> query(final InstrumentDataFieldType fieldType, String value);

}
