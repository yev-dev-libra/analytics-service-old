package com.libra.apollo.analytics.engine;

import java.util.Optional;

import com.libra.apollo.analytics.entity.enums.CompositionType;

public interface Value extends Comparable<Value> {

	public Optional<Object> getValue();
	
	public CompositionType getCompositionType();
	
	public ValueDataFieldType getFieldType();
	
	public default Class<?> getClazz() {
		return getValue().get().getClass();
	};
}
