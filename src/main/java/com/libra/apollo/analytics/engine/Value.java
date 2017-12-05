package com.libra.apollo.analytics.engine;

import java.util.Optional;

public interface Value {

	public Optional<Object> getValue();
	
	public default Class<?> getClazz() {
		return getValue().get().getClass();
	};
}
