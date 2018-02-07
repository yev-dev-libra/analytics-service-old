package com.libra.apollo.analytics.specification;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.StockIndicator;

public class StockIndicatorSpecification<T extends StockIndicator> {

	private static String STOCK_ID = ValueDataFieldType.STOCK_ID.getFieldName();
	private static String stampDateFieldName = ValueDataFieldType.STAMP_DATE.getFieldName();

	public static <T> Specification<T> stockIdEquals(final Long stockId) {

		return (root, query, cb) -> {
			return cb.equal(root.<Long>get(STOCK_ID), stockId);
		};
	}
	public static <T> Specification<T> groupByStockId() {
		
		return (root, query, cb) -> {
			Root<StockIndicator> c = query.from(StockIndicator.class);
			return query.groupBy(c.get(STOCK_ID)).getRestriction();
		};
	}
	public static <T> Specification<T> groupByField(final ValueDataFieldType fieldType) {
		
		return (root, query, cb) -> {
			Root<StockIndicator> c = query.from(StockIndicator.class);
			return query.groupBy(c.get(fieldType.getFieldName())).getRestriction();
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
	
	public static <T> Specification<T> idsEquals(final ValueDataFieldType fieldType, final Collection<Long> ids) {
		return (root, query, cb) -> {
			final Path<Long> stocks = root.<Long>get(fieldType.getFieldName());
			return stocks.in(ids);
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
