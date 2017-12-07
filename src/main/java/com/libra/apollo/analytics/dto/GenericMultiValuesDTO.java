package com.libra.apollo.analytics.dto;

import java.util.List;

import com.libra.apollo.analytics.entity.DomainObject;


@SuppressWarnings("rawtypes")
public class GenericMultiValuesDTO<T extends DomainObject> {

	private List<GenericSingleValueDTO<T>> values;

	public List<GenericSingleValueDTO<T>> getValues() {
		return values;
	}

	public void setValues(List<GenericSingleValueDTO<T>> values) {
		this.values = values;
	}
	
	
	
}
