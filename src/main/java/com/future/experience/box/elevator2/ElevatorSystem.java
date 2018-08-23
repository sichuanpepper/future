package com.future.experience.box.elevator2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 8/21/18.
 */
public class ElevatorSystem implements ElevatorController {
    private List<Elevator> elevatorList = new ArrayList<>();

    private ResponseStrategy responseStrategy = null;

    public ElevatorSystem(List<Elevator> elevatorList, ResponseStrategy responseStrategy) {
        this.elevatorList = elevatorList;
        this.responseStrategy = responseStrategy;
    }

    @Override
    public void startAll() {

    }

    @Override
    public void start(Elevator elevator) {

    }

    @Override
    public void stopAll() {

    }

    @Override
    public void stop(Elevator elevator) {

    }

    @Override
    public void addElevator(Elevator elevator) {

    }

    @Override
    public void removeElevator(Elevator elevator) {

    }

    @Override
    public Elevator request(int curFloor, ElevatorDir dir) {
        return null;
    }
}
