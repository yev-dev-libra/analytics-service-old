package com.libra.apollo.analytics.engine;

public interface Value {

	public Object getValue();
	
	public default Class<?> getClazz() {
		return getValue().getClass();
	};
}
