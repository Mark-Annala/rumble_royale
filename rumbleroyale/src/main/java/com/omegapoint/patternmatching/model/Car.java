package com.omegapoint.patternmatching.model;

public final class Car implements Vehicle{
    public final int passengers;

    public Car(int passengers) {
        this.passengers = passengers;
    }

    public Car() {
        this(0);
    }
}
