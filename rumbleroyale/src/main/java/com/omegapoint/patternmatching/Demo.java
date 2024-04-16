package com.omegapoint.patternmatching;

import com.omegapoint.patternmatching.model.Bus;
import com.omegapoint.patternmatching.model.Car;
import com.omegapoint.patternmatching.model.DeliveryTruck;
import com.omegapoint.patternmatching.model.Taxi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Demo {
    void main() {
        final var tollCalc = new TollCalculator();
        var soloDriver = new Car();
        var twoRideShare = new Car (1);
        var threeRideShare = new Car (2);
        var fullVan = new Car (5);
        var emptyTaxi = new Taxi();
        var singleFare = new Taxi (1);
        var doubleFare = new Taxi (2);
        var fullVanPool = new Taxi (5);
        var lowOccupantBus = new Bus (90, 15);
        var normalBus = new Bus (90,75);
        var fullBus = new Bus (90, 85);

        var heavyTruck = new DeliveryTruck (7500);
        var truck = new DeliveryTruck (4000);
        var lightTruck = new DeliveryTruck (2500);


        System.out.println(STR."The toll for a solo driver is \{tollCalc.calculateToll(soloDriver)}");
        System.out.println(STR."The toll for a two ride share is \{tollCalc.calculateToll(twoRideShare)}");
        System.out.println(STR."The toll for a three ride share is \{tollCalc.calculateToll(threeRideShare)}");
        System.out.println(STR."The toll for a fullVan is \{tollCalc.calculateToll(fullVan)}");

        System.out.println(STR."The toll for an empty taxi is \{tollCalc.calculateToll(emptyTaxi)}");
        System.out.println(STR."The toll for a single fare taxi is \{tollCalc.calculateToll(singleFare)}");
        System.out.println(STR."The toll for a double fare taxi is \{tollCalc.calculateToll(doubleFare)}");
        System.out.println(STR."The toll for a full van taxi is \{tollCalc.calculateToll(fullVanPool)}");

        System.out.println(STR."The toll for a low-occupant bus is \{tollCalc.calculateToll(lowOccupantBus)}");
        System.out.println(STR."The toll for a regular bus is \{tollCalc.calculateToll(normalBus)}");
        System.out.println(STR."The toll for a bus is \{tollCalc.calculateToll(fullBus)}");

        System.out.println(STR."The toll for a truck is \{tollCalc.calculateToll(heavyTruck)}");
        System.out.println(STR."The toll for a truck is \{tollCalc.calculateToll(truck)}");
        System.out.println(STR."The toll for a truck is \{tollCalc.calculateToll(lightTruck)}");


        final List<LocalDateTime> testTimes = List.of(
                        LocalDateTime.of(2019, 3, 4, 8, 0, 0), // morning rush
                        LocalDateTime.of(2019, 3, 6, 11, 30, 0), // daytime
                        LocalDateTime.of(2019, 3, 7, 17, 15, 0), // evening rush
                        LocalDateTime.of(2019, 3, 14, 3, 30, 0), // overnight
                        LocalDateTime.of(2019, 3, 16, 8, 30, 0), // weekend morning rush
                        LocalDateTime.of(2019, 3, 17, 14, 30, 0), // weekend daytime
                        LocalDateTime.of(2019, 3, 17, 18, 5, 0), // weekend evening rush
                        LocalDateTime.of(2019, 3, 16, 1, 30, 0) // weekend overnight
        );
        testTimes.forEach(testTime -> {
            System.out.println(STR."Inbound premium at \{testTime} is \{tollCalc.peakTimePremiumIfElse(testTime, true)}");
            System.out.println(STR."Outbound premium at \{testTime} is \{tollCalc.peakTimePremiumIfElse(testTime, false)}");
        });

        System.out.println("====================================================");

        testTimes.forEach(testTime -> {
            System.out.println(STR."Inbound premium at \{testTime} is \{tollCalc.peakTimePremiumIfElse(testTime, true)}");
            System.out.println(STR."Outbound premium at \{testTime} is \{tollCalc.peakTimePremiumIfElse(testTime, false)}");
        });
        System.out.println("====================================================");
        testTimes.forEach(testTime -> {
            System.out.println(STR."Inbound premium at \{testTime} is \{tollCalc.peakTimePremiumIfElse(testTime, true)}");
            System.out.println(STR."Outbound premium at \{testTime} is \{tollCalc.peakTimePremiumIfElse(testTime, false)}");
        });
    }
}
