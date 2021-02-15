package com.future.experience.didi.dispatch;

public class Car {
    public int location;   //it's better to use private instead of public.

    public int availableSeats;

    public Car(int location, int availableSeats) {
        this.location = location;
        this.availableSeats = availableSeats;
    }
}
