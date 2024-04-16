package com.omegapoint.patternmatching;

import com.omegapoint.patternmatching.model.Bus;
import com.omegapoint.patternmatching.model.Car;
import com.omegapoint.patternmatching.model.DeliveryTruck;
import com.omegapoint.patternmatching.model.Taxi;
import com.omegapoint.patternmatching.model.Vehicle;

import java.time.LocalDateTime;

public class TollCalculator {
    public double calculateToll(final Vehicle vehicle) {
        return switch (vehicle) {
            case null -> throw new IllegalArgumentException("Vehicle cannot be null");
            case Car car -> switch (car.passengers()) {
                case 0 -> 2.00 + 0.5;
                case 1 -> 2.00;
                case 2 -> 2.00 - 0.5;
                default -> 2.0 - 1.0;
            };
            case Taxi(int fares) -> switch (fares) {
                case 0 -> 3.50 + 1.00;
                case 1 -> 3.50;
                case 2 -> 3.50 - 0.50;
                default -> 3.50 - 1.00;
            };
            case Bus(double riders, double capacity) when (riders / capacity) < 0.50 -> 5.00 + 2.00;
            case Bus(double riders, double capacity) when (riders / capacity) > 0.90 -> 5.00 - 1.00;
            case Bus _ -> 5.00;
            case DeliveryTruck(int grossWeight) when grossWeight > 5000 -> 10.0 + 5.0;
            case DeliveryTruck(int grossWeight) when grossWeight < 3000 -> 10.0 - 2.0;
            case DeliveryTruck _ -> 10.0;
        };
    }

    public double peakTimePremiumIfElse(final LocalDateTime timeOfToll, boolean isInbound) {
        final WeekTime weekTime = new WeekTime(timeOfToll, isInbound);
        return switch (weekTime) {
            case WeekTime(Boolean isWeekDay, TimeBand timeband, Boolean inbound) when isWeekDay -> switch (timeband) {
                case TimeBand.Overnight -> 0.75;
                case TimeBand.Daytime -> 1.5;
                case TimeBand.MorningRush -> inbound ? 2.0 : 1.0;
                case TimeBand.EveningRush -> !inbound ? 2.0 : 1.0;
            };
            case WeekTime(Boolean _, TimeBand _, Boolean _) -> 1.0;
        };
    }

    record WeekTime(Boolean isWeekday, TimeBand timeBand, Boolean inbound) {
        public WeekTime(final LocalDateTime timeOfToll, boolean isInbound) {
            this(isWeekDay(timeOfToll), getTimeBand(timeOfToll), isInbound);
        }
    }

    private static boolean isWeekDay(LocalDateTime timeOfToll) {
        return switch (timeOfToll.getDayOfWeek()) {
            case SATURDAY, SUNDAY -> false;
            default -> true;
        };
    }

    private enum TimeBand {
        MorningRush,
        Daytime,
        EveningRush,
        Overnight
    }

    private static TimeBand getTimeBand(LocalDateTime timeOfToll) {
        return switch (timeOfToll.getHour()) {
            case 0, 1, 2, 3, 4, 5, 6, 20, 21, 22, 23 -> TimeBand.Overnight;
            case 7, 8, 9 -> TimeBand.MorningRush;
            case 10, 11, 12, 13, 14, 15, 16 -> TimeBand.Daytime;
            default -> TimeBand.EveningRush;
        };
    }
}
