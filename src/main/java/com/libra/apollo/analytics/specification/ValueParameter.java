package com.libra.apollo.analytics.specification;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.core.Composition;
import com.libra.apollo.analytics.engine.core.Value;

public interface ValueParameter extends Value, Composition {

	public <T> Specification<T> getSpecification();

}
