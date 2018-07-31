package com.kiyosuke.optional.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kiyosuke.optional.function.Consumer;
import com.kiyosuke.optional.function.Function;
import com.kiyosuke.optional.function.Predicate;
import com.kiyosuke.optional.function.Supplier;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class Optional<T> {

    private T optionalValue = null;

    /**
     * NullableなOptionalを生成
     */
    public static <T> Optional<T> ofNullable(@Nullable T value) {
        if (value == null) return empty();
        return new Optional<>(value);
    }

    /**
     * 保持している値がNullの場合何もしない
     * 保持している値がNullでなければ処理を実行する
     */
    public void ifPresent(Consumer<T> present) {
        if (optionalValue == null) return;
        present.accept(optionalValue);
    }


    private Optional(T value) {
        optionalValue = value;
    }


    private Optional() {
    }

    /**
     * 空のOptional生成
     */
    public static <T> Optional<T> empty() {
        return new Optional<>();
    }

    /**
     * 値が条件に一致する場合値を返却
     */
    public Optional<T> filter(Predicate<T> predicate) {
        if (optionalValue == null) return empty();
        if (predicate == null) throw new NullPointerException();
        if (predicate.test(optionalValue)) return Optional.of(optionalValue);
        return empty();
    }

    /**
     * 値がNullかどうかを返却
     */
    public boolean isPresent() {
        return optionalValue != null;
    }

    /**
     * 値に対して処理を行い結果をOptionalでラップし返却
     * 値がNullの場合空のOptionalを返却
     */
    public <R> Optional<R> map(Function<T, R> map) {
        if (optionalValue == null) return empty();
        return Optional.ofNullable(map.apply(optionalValue));
    }

    /**
     * 値を取得
     */
    public T get() {
        if (optionalValue != null) {
            return optionalValue;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * NonNullのOptionalを生成
     */
    public static <T> Optional<T> of(@NonNull T value) {
        return new Optional<>(Objects.requireNonNull(value));
    }

    /**
     * flatten
     */
    public <U> Optional<U> flatMap(Function<T, Optional<U>> mapper) {
        if (optionalValue == null) return empty();
        return mapper.apply(optionalValue);
    }

    /**
     * 値を返却
     * 値がNullの場合は引数に取った値を返す
     */
    public T orElse(T other) {
        if (optionalValue != null) return optionalValue;
        return other;
    }

    /**
     * 値を返却
     * デフォルト値をラムダで指定
     */
    public T orElseGet(Supplier<T> other) {
        if (optionalValue != null) return optionalValue;
        return other.get();
    }

    /**
     * 値を返却
     * 値がNullの場合受け取ったExceptionを投げる
     */
    public <X extends Exception> T orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (optionalValue != null) return optionalValue;
        throw exceptionSupplier.get();
    }

}
