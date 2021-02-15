package com.future.experience.didi.dispatch;

import java.util.ArrayList;
import java.util.List;

public class CarManagement {
    private List<Car> cars = new ArrayList<>();

    private static CarManagement instance = null;

    private CarManagement() {
        //init cars here...
    }

    public static CarManagement getInstance() {
        if(instance == null)  instance = new CarManagement();
        return instance;
    }

    public List<Car> getCars(int numberOfCar, ICarSort carSort) {
        if(numberOfCar > this.cars.size()) {
            //do something handle this issue...
        }

        return carSort.order(this.cars).subList(0, numberOfCar);
    }
}
