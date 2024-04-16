package com.omegapoint.patternmatching.model;

public record Car(int passengers) implements Vehicle {
    public Car() {
        this(0);
    }
}
