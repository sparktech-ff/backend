package com.sparktechcode.ff.core.common.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pair<First, Second> {
    private First first;
    private Second second;

    public static <First, Second> Pair<First, Second> of(First first, Second second) {
        return new Pair<>(first, second);
    }

    public String join(String separator) {
        return Stream.of(first, separator, second)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }
}
