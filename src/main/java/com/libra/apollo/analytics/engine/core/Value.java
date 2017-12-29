package com.libra.apollo.analytics.engine.core;

import java.util.Optional;

public interface Value extends Comparable<Value> {

	public Optional<Object> getValue();
	
	public ValueDataFieldType getFieldType();
	
	public default Class<?> getClazz() {
		return getValue().get().getClass();
	};
}
