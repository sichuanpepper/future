package com.future.experience.box.elevator;

/**
 * Created by xingfeiy on 8/12/18.
 */
public class Elevator implements RequestExecutor {
    private int id = 0;

    private int floors = 0;

    private int capacity = 0;

    private ElevatorRunningInfo runningInfo = new ElevatorRunningInfo();


    public Elevator(int id, int floors, int capacity) {
        this.id = id;
        this.floors = floors;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public int getFloors() {
        return floors;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean execute(ElevatorRequest request) {
        System.out.println("Elevator " + id + " taking request " + request.getFloor() + "=>" + request.getDirection());

        return false;
    }

    public boolean isFull() {
        return this.runningInfo.getPeopleInside() >= this.capacity;
    }

    public ElevatorRunningInfo getRunningInfo() {
        return runningInfo;
    }

    public int distanceTo(int dest) {
        int curFloor = runningInfo.getCurFloor();
        if(this.runningInfo.getStatus().equals(ElevatorStatus.GOING_UP)) {
            if(curFloor <= dest) {
                return dest - curFloor;
            } else {
                return (this.floors - curFloor) + (this.floors - dest);
            }
        } else if(this.runningInfo.getStatus().equals(ElevatorStatus.GOING_DOWN)) {
            return curFloor >= dest ? curFloor - dest : (curFloor + dest);
        }
        return 0;
    }
}
