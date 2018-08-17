package com.future.experience.box.elevator;

/**
 * - Scope: Multiple elevators work on single building
 * - Entities:
 *  - Elevator
 *  - User
 *
 * - Use cases
 *  - Elevators stand by on the default floor if there's no pending request.
 *  - User press button to request an elevator
 *      - The request can't be cancel.
 *      - A request was sent to elevator controller.
 *      - A elevator takes the request
 *  - User enter the elevator and press floor button to select destination
 *      - The selected floors are cancellable
 *  - User left from elevator
 *
 *  - Elevator response strategy
 *      - Quick response
 *          - Find the closest elevator, once the elevator is selected, no change anymore.
 *          - Once the elevator accepted a request, process this request directly without interrupt.
 *      - Efficient usage
 *          - Elevators pick up the requests on the fly
 *
 *
 * Created by xingfeiy on 8/14/18.
 */
public class Design {
}
