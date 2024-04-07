package com.omegapoint.patternmatching.model;

public final class Bus implements Vehicle{
    public final double capacity;
    public final double riders;

    public Bus(int capacity, int riders) {
        this.capacity = capacity;
        this.riders = riders;
    }
}
