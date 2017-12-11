package com.libra.apollo.analytics.specification;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.Value;

public interface ValueParameter<T> extends Comparable<ValueParameter<T>>, Value {

	public Specification<T> getSpecification(ValueParameter<T> parameter);

}
