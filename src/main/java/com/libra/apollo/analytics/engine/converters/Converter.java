package com.libra.apollo.analytics.engine.converters;

@FunctionalInterface
public interface Converter<F,T> {

	T convert(F from);
}
