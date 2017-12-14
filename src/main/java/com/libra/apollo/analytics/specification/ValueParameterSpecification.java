package com.libra.apollo.analytics.specification;

import org.springframework.data.jpa.domain.Specification;

//TODO: pass a functional handler
public interface ValueParameterSpecification<T> {
	public Specification<T> query(final ValueParameter value);
}
