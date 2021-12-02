package ru.mipt.bit.platformer.converters;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter
 * */
public interface Converter<T, S> {
    T convert(S objectToConvert);

    default List<T> convert(List<S> objectsToConvert) {
        return objectsToConvert
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
