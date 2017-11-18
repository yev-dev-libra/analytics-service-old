package com.libra.apollo.analytics.engine;

public interface Adapter<F,T> {

	T adapt(F from);
}
