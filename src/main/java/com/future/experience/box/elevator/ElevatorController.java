package com.future.experience.box.elevator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xingfeiy on 8/14/18.
 */
public class ElevatorController implements Runnable {
    private ElevatorSelector elevatorSelector = null;

    private ElevatorPool elevatorPool = null;

    private BlockingQueue<ElevatorRequest> requests = new ArrayBlockingQueue<>(100);

    public ElevatorController(ElevatorSelector elevatorSelector, ElevatorPool elevatorPool) {
        this.elevatorSelector = elevatorSelector;
        this.elevatorPool = elevatorPool;
    }

    public synchronized void request(ElevatorRequest request) {
        System.out.println("Request: " + request.getFloor() + "=>" + request.getDirection());
        this.requests.offer(request);
    }


    @Override
    public void run() {
        System.out.println("Elevator System is running!");
        ElevatorRequest req = null;
        try {
            while (!(req = requests.take()).equals(ElevatorRequest.STOP_ALL)) {
                Elevator elevator = elevatorSelector.select(req, elevatorPool);
                elevator.execute(req);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Elevator System is stopped!");
    }
}
