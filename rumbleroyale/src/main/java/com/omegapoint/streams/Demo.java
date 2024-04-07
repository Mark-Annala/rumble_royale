package com.omegapoint.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Demo {
    static final String QUOTE = "This is a pretty bad quote";
    static final Stream<String> stream = Arrays.stream(QUOTE.split(" "));

    void main(){
        stream.sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                .forEach(System.out::println);
        final Optional<String> any = Arrays.stream(QUOTE.split(" "))
                .findAny();
//        System.out.println(any);
    }
}
