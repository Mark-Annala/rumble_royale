package com.omegapoint.patternmatching.model;

public final record Taxi(int fares) implements Vehicle {

    public Taxi() {
        this(0);
    }
}
