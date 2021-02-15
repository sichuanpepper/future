package com.future.experience.gugou;

public class ParkingLots {
    public abstract class Car{}

    public class SmallCar extends Car {}

    public class MediumCar extends Car {}

    public class LargeCar extends Car {}

    public abstract class ParkingLot {}

    public interface Charageable {}

    public class OurParkingLot extends ParkingLot implements Charageable {}
}
