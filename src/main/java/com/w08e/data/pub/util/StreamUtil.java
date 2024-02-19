package com.w08e.data.pub.util;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StreamUtil {
	public StreamUtil() {
	}

	public static <T, R> List<R> map(Iterable<T> iterable, Function<? super T, ? extends R> mapper) {
		return (List) StreamSupport.stream(iterable.spliterator(), false).map(mapper).collect(Collectors.toList());
	}

	public static <T, K> Map<K, T> toMap(Iterable<T> iterable, Function<? super T, ? extends K> keyMapper) {
		return toMap(iterable, keyMapper, t -> t);
	}

	public static <T, K, U> Map<K, U> toMap(Iterable<T> iterable, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
		return (Map) StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toMap(keyMapper, valueMapper));
	}

	public static <T, K, U> Map<K, U> toMap(Iterable<T> iterable, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
		return (Map) StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
	}

	public static <T, K> Map<K, List<T>> group(Iterable<T> iterable, Function<? super T, ? extends K> classifier) {
		return (Map) StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.groupingBy(classifier));
	}
}
