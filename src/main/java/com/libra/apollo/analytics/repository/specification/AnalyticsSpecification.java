package com.libra.apollo.analytics.repository.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

public interface AnalyticsSpecification<T> {

	public Specification<T> stampDateEqual(final Date date);

	public Specification<T> stampDateGreaterThanOrEqual(final Date date);

	public Specification<T> stampDateLessThanOrEqual(final Date date);
}