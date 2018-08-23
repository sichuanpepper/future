package com.future.experience.box.elevator2;

import java.util.List;

/**
 * Created by xingfeiy on 8/21/18.
 */
public interface ResponseStrategy {
    Elevator response(List<Elevator> elevators, ElevatorRequest request);
}
