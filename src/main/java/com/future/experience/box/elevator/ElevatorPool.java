package com.future.experience.box.elevator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 8/12/18.
 */
public class ElevatorPool {
    private List<Elevator> elevators = new ArrayList<>();


    public ElevatorPool(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public boolean remove(Elevator elevator) {
        if(elevator.getRunningInfo().getStatus().equals(ElevatorStatus.GOING_DOWN) ||
                elevator.getRunningInfo().getStatus().equals(ElevatorStatus.GOING_UP) ||
                elevator.getRunningInfo().getStatus().equals(ElevatorStatus.HOLDING)) return false;
        return this.elevators.remove(elevator);
    }

    public void add(Elevator elevator) {
        this.elevators.add(elevator);
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
