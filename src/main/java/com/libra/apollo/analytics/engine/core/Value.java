package com.libra.apollo.analytics.engine.core;

import java.util.Optional;

public interface Value extends Comparable<Value>, ValueDataField {

	public Optional<Object> getValue();
	
	public default Class<?> getClazz() {
		return getValue().get().getClass();
	};
}
