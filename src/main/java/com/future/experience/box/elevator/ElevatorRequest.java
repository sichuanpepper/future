package com.future.experience.box.elevator;

import java.sql.Timestamp;

/**
 * Created by xingfeiy on 8/14/18.
 */
public class ElevatorRequest {
    private int floor;

    private ElevatorDirection direction;

    private Timestamp tstamp = null;

    public static final ElevatorRequest STOP_ALL = new ElevatorRequest(Integer.MIN_VALUE, ElevatorDirection.DOWN);

    public ElevatorRequest(int floor, ElevatorDirection direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public ElevatorDirection getDirection() {
        return direction;
    }
}
