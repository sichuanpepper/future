package com.future.experience.box.elevator;

/**
 * Created by xingfeiy on 8/14/18.
 */
public interface ElevatorSelector {
    Elevator select(ElevatorRequest request, ElevatorPool pool);
}
