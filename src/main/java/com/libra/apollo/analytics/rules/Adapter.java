package com.libra.apollo.analytics.rules;

public interface Adapter<F,T> {

	T adapt(F from);
}
