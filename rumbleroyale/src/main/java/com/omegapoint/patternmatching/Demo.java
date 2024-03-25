package com.omegapoint.patternmatching;

public class Demo {
    void main() {
        Vehicle vehicle = new Boat();
        System.out.println(tellMeWhichVehicleItIS(vehicle));
    }

    String tellMeWhichVehicleItIS(final Vehicle vehicle) {
        return switch (vehicle) {
            case Truck t when t.weight() > 30 -> "It's a Truck!";
            case Truck truck -> "Its a small Truck";
            case Moped m -> "It's a Moped!";
            case Other other -> switch (other){
                case Boat b -> "Its a boat!";
                case Other o -> "Something wierd...";
            };
        };
    }
}


sealed interface Vehicle permits Truck, Moped, Other {
    int weight();
}

final class Truck implements Vehicle {
    @Override
    public int weight() {
        return 2000;
    }
};

final class Moped implements Vehicle {
    @Override
    public int weight() {
        return 50;
    }
};

non-sealed class Other implements Vehicle {
    @Override
    public int weight() {
        return 300;
    }
};

class Boat extends Other{};
