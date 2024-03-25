package com.omegapoint.streams;

import java.util.Arrays;
import java.util.Optional;

public class Demo {
    static final String QUOTE = "This is a pretty bad quote";

    void main(){
        final Optional<String> any = Arrays.stream(QUOTE.split(" "))
                .findAny();
        System.out.println(any);
    }
}
