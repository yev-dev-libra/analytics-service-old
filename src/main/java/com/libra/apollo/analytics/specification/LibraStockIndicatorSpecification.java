package com.libra.apollo.analytics.specification;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.Path;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.ValueDataFieldType;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

public class LibraStockIndicatorSpecification<T extends LibraStockIndicator> {

	private static String STOCK_ID = ValueDataFieldType.STOCK_ID.getFieldName();

	public static <T> Specification<T> stockIdEquals(final Long stockId) {

		return (root, query, cb) -> {
			return cb.equal(root.<Long>get(STOCK_ID), stockId);
		};
	}

	public static <T> Specification<T> stockIdsEquals(final Collection<Long> stockIds) {
		return (root, query, cb) -> {
			final Path<Long> stocks = root.<Long>get(STOCK_ID);
			return stocks.in(stockIds);
		};
	}
	
	public static <T> Specification<T> idEquals(final ValueDataFieldType fieldType, final Long stockId) {
		
		return (root, query, cb) -> {
			return cb.equal(root.<Long>get(fieldType.getFieldName()), stockId);
		};
	}
	
	public static <T> Specification<T> idsEquals(final List<Long> stockIds) {
		return (root, query, cb) -> {
			final Path<Long> stocks = root.<Long>get(STOCK_ID);
			return stocks.in(stockIds);
		};
	}

	//----------------------------- Doubles -----------------------------------------------------------//
	
	public static <T> Specification<T> fieldEqualsTo(final ValueDataFieldType fieldType, Double value) {
		return (root, query, cb) -> {
			return cb.equal(root.<BigDecimal>get(fieldType.getFieldName()), BigDecimal.valueOf(value.doubleValue()));
		};
	}
	public static <T> Specification<T> fieldNotEqualsTo(final ValueDataFieldType fieldType, Double value) {
		return (root, query, cb) -> {
			return cb.notEqual(root.<BigDecimal>get(fieldType.getFieldName()), BigDecimal.valueOf(value.doubleValue()));
		};
	}
	public static <T> Specification<T> fieldGreaterThan(final ValueDataFieldType fieldType, Double value) {
		return (root, query, cb) -> {
			return cb.greaterThan(root.<BigDecimal>get(fieldType.getFieldName()),BigDecimal.valueOf(value.doubleValue()));
		};
	}
	

	public static <T> Specification<T> fieldLessThanOrEqualTo(final ValueDataFieldType fieldType, Double value) {
		return (root, query, cb) -> {
			return cb.lessThanOrEqualTo(root.<BigDecimal>get(fieldType.getFieldName()), BigDecimal.valueOf(value));
		};
	}

	public static <T> Specification<T> fieldLessThan(final ValueDataFieldType fieldType, Double value) {
		return (root, query, cb) -> {
			return cb.lessThan(root.<BigDecimal>get(fieldType.getFieldName()), BigDecimal.valueOf(value));
		};
	}

	public static <T> Specification<T> fieldGreaterThanOrEqualTo(final ValueDataFieldType fieldType,Double value) {
		return (root, query, cb) -> {
			return cb.greaterThanOrEqualTo(root.<BigDecimal>get(fieldType.getFieldName()), BigDecimal.valueOf(value));
		};
	}
	
	//----------------------------- BigDecimals -----------------------------------------------------------//
	
	public static <T> Specification<T> fieldEqualsTo(final ValueDataFieldType fieldType, BigDecimal value) {
		return (root, query, cb) -> {
			return cb.equal(root.<BigDecimal>get(fieldType.getFieldName()), value);
		};
	}
	public static <T> Specification<T> fieldNotEqualsTo(final ValueDataFieldType fieldType, BigDecimal value) {
		return (root, query, cb) -> {
			return cb.notEqual(root.<BigDecimal>get(fieldType.getFieldName()), value);
		};
	}
	
	public static <T> Specification<T> fieldGreaterThan(final ValueDataFieldType fieldType, BigDecimal value) {
		return (root, query, cb) -> {
			return cb.greaterThan(root.<BigDecimal>get(fieldType.getFieldName()),value);
		};
	}
	
	
	public static <T> Specification<T> fieldLessThanOrEqualTo(final ValueDataFieldType fieldType, BigDecimal value) {
		return (root, query, cb) -> {
			return cb.lessThanOrEqualTo(root.<BigDecimal>get(fieldType.getFieldName()), value);
		};
	}
	
	public static <T> Specification<T> fieldLessThan(final ValueDataFieldType fieldType, BigDecimal value) {
		return (root, query, cb) -> {
			return cb.lessThan(root.<BigDecimal>get(fieldType.getFieldName()), value);
		};
	}
	
	public static <T> Specification<T> fieldGreaterThanOrEqualTo(final ValueDataFieldType fieldType,BigDecimal value) {
		return (root, query, cb) -> {
			return cb.greaterThanOrEqualTo(root.<BigDecimal>get(fieldType.getFieldName()), value);
		};
	}

}
