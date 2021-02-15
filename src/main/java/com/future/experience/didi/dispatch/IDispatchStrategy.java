package com.future.experience.didi.dispatch;

import java.util.List;
import java.util.Map;

public interface IDispatchStrategy {
    Map<Car, List<Order>> dispatch(List<Car> cars, List<Order> orders);
}
