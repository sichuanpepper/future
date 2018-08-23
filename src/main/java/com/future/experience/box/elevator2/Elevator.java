package com.future.experience.box.elevator2;

import java.util.PriorityQueue;

/**
 * Created by xingfeiy on 8/21/18.
 */
public class Elevator implements ElevatorOperator, ElevatorMovement {
    private int id = 0;

    private int floors = 10;

    private int capacity = 10;

    private int curFloor = 0;

    public Elevator(int id, int floors, int capacity) {
        this.id = id;
        this.floors = floors;
        this.capacity = capacity;
    }

    private PriorityQueue<Integer> requests = new PriorityQueue<>();


    @Override
    public void requestFloor(int floor) {
        if(!requests.contains(floor)) requests.offer(floor);
    }

    @Override
    public void cancelFloor(int floor) {
        requests.remove(floor);
    }

    @Override
    public void callForHelp() {

    }

    @Override
    public void goingUp() {
        if(this.requests.size() == 0) return;
    }

    @Override
    public void goingDown() {

    }

    @Override
    public void standBy() {

    }
}
