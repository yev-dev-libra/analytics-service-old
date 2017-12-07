package com.libra.apollo.analytics.dto;

import com.libra.apollo.analytics.entity.DomainObject;

@SuppressWarnings("rawtypes")
public class GenericSingleValueDTO<T extends DomainObject> {

	private T value;

	
	public GenericSingleValueDTO(T value) {
		super();
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	
}
