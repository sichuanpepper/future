package com.future.experience.didi.dispatch;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleStrategy implements IDispatchStrategy {
    public Map<Car, List<Order>> dispatch(List<Car> cars, List<Order> orders) {
        Map<Car, List<Order>> result = new HashMap<>();
        if (cars == null || orders == null || cars.size() < 1 || orders.size() < 1) return result;
        //todo
        return result;
    }
}
