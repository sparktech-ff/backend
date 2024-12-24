package com.sparktechcode.ff.core.common.payloads;

import java.util.function.Consumer;

public record ErrorResult<D>(D data, Object error, String message) {

    public ErrorResult<D> and(Consumer<D> consumer) {
        consumer.accept(data);
        return this;
    }

    public static <D> ErrorResult<D> of(D data) {
        return new ErrorResult<>(data, null, "Success");
    }

    public static <D> ErrorResult<D> of(D data, Object error) {
        return new ErrorResult<>(data, error, "Success");
    }

    public static <D> ErrorResult<D> of(D data, Object error, String message) {
        return new ErrorResult<>(data, error, message);
    }
}
