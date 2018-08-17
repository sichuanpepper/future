package com.future.experience.box.elevator;

/**
 * Created by xingfeiy on 8/14/18.
 */
public class ElevatorRunningInfo {
    private int curFloor = 0;

    private int peopleInside = 0;

    private ElevatorStatus status = ElevatorStatus.WAITING;

    public int getCurFloor() {
        return curFloor;
    }

    public void setCurFloor(int curFloor) {
        this.curFloor = curFloor;
    }

    public int getPeopleInside() {
        return peopleInside;
    }

    public void setPeopleInside(int peopleInside) {
        this.peopleInside = peopleInside;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }
}
