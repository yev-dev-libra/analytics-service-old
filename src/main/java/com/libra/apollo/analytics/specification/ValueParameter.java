package com.libra.apollo.analytics.specification;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.Value;

public interface ValueParameter extends Comparable<ValueParameter>, Value {

	public <T> Specification<T> getSpecification();

	public <T> Specification<T> getSpecification(ValueParameter parameter);

}
