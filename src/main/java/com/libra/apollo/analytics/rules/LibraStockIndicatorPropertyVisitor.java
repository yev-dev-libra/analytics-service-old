package com.libra.apollo.analytics.rules;

import com.libra.apollo.analytics.entity.Parameter;
import com.libra.apollo.analytics.specification.ValueParameter;

public class LibraStockIndicatorPropertyVisitor<T> implements Visitor<T> {

	@Override
	public T visit(ValueParameter parameter) {
		return null;
	}


}
