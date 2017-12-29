package com.libra.apollo.analytics.engine.core;

import java.util.Date;
import java.util.Optional;

public class DateValue implements Value {

	private Date value;
	
	@Override
	public int compareTo(Value o) {
		return value.compareTo((Date)o.getValue().get());
	}

	@Override
	public Optional<Object> getValue() {
		return Optional.ofNullable(value);
	}

	@Override
	public ValueDataFieldType getFieldType() {
		return null;
	}

}
