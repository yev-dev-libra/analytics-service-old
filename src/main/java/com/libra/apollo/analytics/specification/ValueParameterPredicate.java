package com.libra.apollo.analytics.specification;

import javax.persistence.criteria.Predicate;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;

public interface ValueParameterPredicate {

	public Predicate query(final ValueDataFieldType fieldType, final ValueParameter value);
}
