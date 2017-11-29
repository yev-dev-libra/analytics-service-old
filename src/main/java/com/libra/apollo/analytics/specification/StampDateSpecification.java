package com.libra.apollo.analytics.specification;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Path;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.ValueDataFieldType;

public class StampDateSpecification {

	private static String stampDateFieldName = ValueDataFieldType.STAMP_DATE.getFieldName();

	public static <T> Specification<T> stampDateEqual(final Date stampDate) {
		return (root, query,cb) -> {
			return cb.equal(root.<Date>get(stampDateFieldName), stampDate);
		};
		

	}
	
	public static <T> Specification<T> stampDateLessOrGreater(final Date date) {
		return  (root, query,cb) -> {
			final Path<Date> stampDateRoot = root.<Date>get(stampDateFieldName);
			Predicate p1 = cb.greaterThanOrEqualTo(stampDateRoot, date);
			Predicate p2 = cb.lessThanOrEqualTo(stampDateRoot, date);
			Predicate composite = cb.and(p1,p2);
			return composite;
		};

	}
	public static <T> Specification<T> stampDateEqual(final LocalDate stampDate) {
		return (root, query,cb) -> {
			return cb.equal(root.<LocalDate>get(stampDateFieldName), stampDate);
		};
		
		
	}
	
	public static <T> Specification<T> stampDateLessOrGreater(final LocalDate date) {
		return  (root, query,cb) -> {
			final Path<LocalDate> stampDateRoot = root.<LocalDate>get(stampDateFieldName);
			Predicate p1 = cb.greaterThanOrEqualTo(stampDateRoot, date);
			Predicate p2 = cb.lessThanOrEqualTo(stampDateRoot, date);
			Predicate composite = cb.and(p1,p2);
			return composite;
		};
		
	}

	public static <T> Specification<T> stampDateGreaterThanOrEqual(final Date stampDate) {
		
		return (root, query,cb) -> {
			return cb.greaterThanOrEqualTo(root.<Date>get(stampDateFieldName), stampDate);
		};

	}

	public static <T> Specification<T> stampDateLessThanOrEqual(final Date stampDate) {
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.lessThanOrEqualTo(root.<Date>get(stampDateFieldName), stampDate);
			}

		};

	}
}
