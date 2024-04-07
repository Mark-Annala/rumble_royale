package com.omegapoint.patternmatching.model;

public final class Taxi implements Vehicle{
    public final int fares;

    public Taxi(int fares) {
        this.fares = fares;
    }

    public Taxi() {
        this(0);
    }
}
