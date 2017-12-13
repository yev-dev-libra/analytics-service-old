package com.libra.apollo.analytics.specification;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.ValueDataFieldType;

//TODO: pass a functional handler
public interface ValueParameterSpecification<T> {
	public Specification<T> query(final ValueDataFieldType fieldType, final ValueParameter value);
}
