package com.kiyosuke.optional.function;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T value);
}
