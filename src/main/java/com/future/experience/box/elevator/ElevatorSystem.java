package com.future.experience.box.elevator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 8/14/18.
 */
public class ElevatorSystem {
    public static void main(String[] args) {
        List<Elevator> elevators = new ArrayList<>();
        elevators.add(new Elevator(1, 50, 20));
        elevators.add(new Elevator(2, 50, 20));
        ElevatorPool pool = new ElevatorPool(elevators);

        ElevatorController controller = new ElevatorController(new QuickResponseSelector(), pool);
        Thread controllerThread = new Thread(controller);
        controllerThread.start();

        controller.request(new ElevatorRequest(0, ElevatorDirection.UP));

        controller.request(new ElevatorRequest(0, ElevatorDirection.UP));
        controller.request( ElevatorRequest.STOP_ALL);
    }
}
