package com.libra.apollo.analytics.specification;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.ValueDataFieldType;

public interface ValueParameterSpecification {
	public <T> Specification<T> query(final ValueDataFieldType fieldType, final ValueParameter value);
}
