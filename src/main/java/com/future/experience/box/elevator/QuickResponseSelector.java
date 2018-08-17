package com.future.experience.box.elevator;

/**
 * Created by xingfeiy on 8/14/18.
 */
public class QuickResponseSelector implements ElevatorSelector {
    @Override
    public Elevator select(ElevatorRequest request, ElevatorPool pool) {
        Elevator candidate = null;
        int shortest = Integer.MAX_VALUE;
        for(Elevator elevator : pool.getElevators()) {
            if(elevator.isFull()) continue;
            int dis = distance(elevator, request.getFloor());
            if(dis < shortest) {
                candidate = elevator;
                shortest = dis;
            }
        }
        return candidate;
    }

    private int distance(Elevator elevator, int dest) {
        return 0;
    }
}
