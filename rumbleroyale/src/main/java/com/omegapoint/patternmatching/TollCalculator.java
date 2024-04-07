package com.omegapoint.patternmatching;

import com.omegapoint.patternmatching.model.Bus;
import com.omegapoint.patternmatching.model.Car;
import com.omegapoint.patternmatching.model.DeliveryTruck;
import com.omegapoint.patternmatching.model.Taxi;
import com.omegapoint.patternmatching.model.Vehicle;

public class TollCalculator {
    public double calculateToll(Vehicle vehicle) {
        return switch (vehicle){
            case null -> throw new IllegalArgumentException("Vehicle cannot be null");
            case Car car -> switch (car.passengers){
                case 0 -> 2.00 + 0.5;
                case 1 -> 2.00;
                case 2 -> 2.00 - 0.5;
                default -> 2.0 - 1.0;
            };
            case Taxi taxi -> switch (taxi.fares){
                case 0  ->  3.50 + 1.00;
                case 1  ->  3.50;
                case 2  ->  3.50 - 0.50;
                default ->  3.50 - 1.00;
            };
            case Bus bus when (bus.riders/bus.capacity) < 0.50 -> 5.00 + 2.00;
            case Bus bus when (bus.riders/bus.capacity) > 0.90 -> 5.00 - 1.00;
            case Bus _ -> 5.00;
            case DeliveryTruck deliveryTruck when deliveryTruck.grossWeigth > 5000 -> 10.0 + 5.0;
            case DeliveryTruck deliveryTruck when deliveryTruck.grossWeigth < 3000 -> 10.0 - 2.0;
            case DeliveryTruck _ -> 10.0;
        };
    }
}
