package com.future.experience.didi.car;

import java.util.List;

public interface IDealer {
    void addCar(Car car);

    List<Car> search(String maker, String model);

    void soldCar(Car car);
}
