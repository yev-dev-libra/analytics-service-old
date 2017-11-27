package com.libra.apollo.analytics.rules;

import com.libra.apollo.analytics.specification.ValueParameter;

/*
 * Modified visitor pattern. To return a "standard" visitor return <Void> type
 */
public interface Visitor<T> {

	public T visit(ValueParameter parameter);
}
