package com.makstat.demo.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Streams {

    public static <T> Stream<T> asStream(final List<T> list) {
        return Optional.ofNullable(list).map(List::stream).orElseGet(Stream::empty);
    }
}
