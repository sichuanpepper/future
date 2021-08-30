package com.future.generic;

/**
 * Let's start from single elevator.
 *
 * Request flow:
 * external button -> request -> elevator system (handle request and assign request to an elevator)
 * elevator -> handle request and store request to an execute queue -> process requests.
 *
 * Request:
 *  - level, where the request was sent.
 *  - two different request, external request and internal request(where to go).
 *
 * ElevatorSystem
 *  - Init elevators
 *  - Add/remove elevator
 *  - Handle request  (interface: RequestHandler)
 *  - Stop
 *
 * Elevator implements ElevatorOperation
 *  - Capacity
 *  - Start/end level
 *  - Buttons
 *
 * ElevatorOperation (interface)
 *  - Move Up/Down
 *  - Receive request
 *  - Status: empty/load/overload
 *
 *
 */
public class OODElevator {
    private class Request {
        public int curLevel;
    }

    private class ExternalRequest extends Request {}

    private class InternalRequest extends Request {}

    private enum Direction {
        UP,
        DOWN
    }

    private enum Status {
        EMPTY,
        LOAD,
        OVERLOAD
    }


    interface ElevatorOperation {
        void handleRequest(Request request);

        void Move(Direction dir);

        Status getStatus();
    }

    class Elevator implements ElevatorOperation {
        public int capacity;

        public int startLevel;

        public int endLevel;

        @Override
        public void handleRequest(Request request) {

        }

        @Override
        public void Move(Direction dir) {

        }

        @Override
        public Status getStatus() {
            return null;
        }
    }
}
