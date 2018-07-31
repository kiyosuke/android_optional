package com.kiyosuke.optional.function;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
