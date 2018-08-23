package com.future.experience.box.elevator2;

/**
 * Created by xingfeiy on 8/21/18.
 */
public interface ElevatorController {
    void startAll();

    void start(Elevator elevator);

    void stopAll();

    void stop(Elevator elevator);

    void addElevator(Elevator elevator);

    void removeElevator(Elevator elevator);

    Elevator request(int curFloor, ElevatorDir dir);

}
