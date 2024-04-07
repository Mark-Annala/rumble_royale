package com.omegapoint.patternmatching.model;

public sealed interface Vehicle permits Bus, Car, DeliveryTruck, Taxi {
}
