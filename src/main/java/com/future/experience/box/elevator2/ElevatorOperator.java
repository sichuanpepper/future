package com.future.experience.box.elevator2;

/**
 * Created by xingfeiy on 8/21/18.
 */
public interface ElevatorOperator {
    void requestFloor(int floor);

    void cancelFloor(int floor);

    void callForHelp();
}
