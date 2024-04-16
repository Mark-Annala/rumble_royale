package com.omegapoint.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class Demo {
    static final String QUOTE = "This is a pretty bad quote, but still better then C#";
    static final Stream<String> stream = Arrays.stream(QUOTE.split(" "));

    void main() {

    }



























    record Tuple(double average, double variance) {
    }

    private Gatherer<Integer, ?, Double> varianceGatherer() {
        return Gatherer.ofSequential(() -> new ArrayList<Integer>(),
                (state, element, downstream) -> !downstream.isRejecting() && state.add(element),
                (integers, downstream) -> downstream.push(calculateVariance(integers)));
    }

    private Collector<Integer, ?, Double> averageCollector() {
        return Collectors.averagingInt(Integer::intValue);
    }

    private Collector<Integer, ?, Double> varianceCollector() {
        return Collectors.collectingAndThen(Collectors.toList(), Demo::calculateVariance);
    }

    private static Double calculateVariance(final List<Integer> integers) {
        final double average = integers.stream().collect(Collectors.averagingInt(Integer::intValue));
        return integers.stream()
                .map(value -> value - average)
                .mapToDouble(value -> Math.pow(value, 2))
                .average()
                .orElse(0);
    }
}
