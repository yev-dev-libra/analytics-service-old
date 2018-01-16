package com.libra.apollo.analytics.engine.converter;

@FunctionalInterface
public interface Converter<F,T> {

	T convert(F from);
}
