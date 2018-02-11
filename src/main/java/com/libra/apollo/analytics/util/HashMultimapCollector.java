package com.libra.apollo.analytics.util;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;

public class HashMultimapCollector<T, K, V> implements Collector<T, HashMultimap<K, V>, HashMultimap<K, V>> {

	private final Function<T, K> keyGetter;
	private final Function<T, V> valueGetter;

	public HashMultimapCollector(Function<T, K> keyGetter, Function<T, V> valueGetter) {
		this.keyGetter = keyGetter;
		this.valueGetter = valueGetter;
	}

	public static <T, K, V> HashMultimapCollector<T, K, V> toMultimap(Function<T, K> keyGetter,
			Function<T, V> valueGetter) {
		return new HashMultimapCollector<>(keyGetter, valueGetter);
	}

	public static <T, K, V> HashMultimapCollector<T, K, T> toMultimap(Function<T, K> keyGetter) {
		return new HashMultimapCollector<>(keyGetter, v -> v);
	}

	@Override
	public Supplier<HashMultimap<K, V>> supplier() {
		return HashMultimap::create;
	}

	@Override
	public BiConsumer<HashMultimap<K, V>, T> accumulator() {
		return (map, element) -> map.put(keyGetter.apply(element), valueGetter.apply(element));
	}

	@Override
	public BinaryOperator<HashMultimap<K, V>> combiner() {
		return (map1, map2) -> {
			map1.putAll(map2);
			return map1;
		};
	}

	@Override
	public Function<HashMultimap<K, V>, HashMultimap<K, V>> finisher() {
		return map -> map;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return ImmutableSet.of(Characteristics.IDENTITY_FINISH);
	}
}
