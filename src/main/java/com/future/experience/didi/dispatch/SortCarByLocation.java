package com.future.experience.didi.dispatch;

import java.util.Collections;
import java.util.List;

public class SortCarByLocation implements ICarSort {
    public List<Car> order(List<Car> cars) {
        Collections.sort(cars, (o1, o2)->(o1.location - o2.location));
        return cars;
    }
}
