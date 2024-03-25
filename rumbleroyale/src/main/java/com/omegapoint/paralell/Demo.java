package com.omegapoint.paralell;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

public class Demo {
    static final List<String> URLS = IntStream.range(0, 500).mapToObj(_ -> "www.google.com").toList();

    void main() throws Exception {
        URLS.stream()
                .gather(Gatherers.mapConcurrent(500, this::call))
                .toList();
    }

    Callable<Void> call(final String url) {
        System.out.println("Calling: " + url);
        return () -> null;
    }
}
