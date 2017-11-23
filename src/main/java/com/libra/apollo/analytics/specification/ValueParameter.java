package com.libra.apollo.analytics.specification;

import org.springframework.data.jpa.domain.Specification;

public interface ValueParameter extends Comparable<ValueParameter>  {

	public Object getValue();
	public <T> Specification<T>  getSpecification();
	public <T> Specification<T>  getSpecification(ValueParameter parameter);

	public default Class<?> getClazz() {
		return getValue().getClass();
	};

}
