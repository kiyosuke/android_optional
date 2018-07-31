package com.kiyosuke.optional.function;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
