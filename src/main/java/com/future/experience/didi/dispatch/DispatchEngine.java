package com.future.experience.didi.dispatch;

import java.util.*;

public class DispatchEngine {
    class LocPoint {
        public int location;

        public boolean isStart;

        public LocPoint(int location, boolean isStart) {
            this.location = location;
            this.isStart = isStart;
        }
    }

    private static DispatchEngine instance = null;

    private DispatchEngine() {}

    public static DispatchEngine getInstance() {
        if(instance == null) instance = new DispatchEngine();
        return instance;
    }

    //check if the given car can finish all orders.
    public boolean canFinish(Car car, List<Order> orders) {
        if(car == null || car.availableSeats < 1 || orders == null) return false;
        List<DispatchEngine.LocPoint> points = new ArrayList<>(2 * orders.size());
        for(Order order : orders) {
            points.add(new LocPoint(order.departure, true));
            points.add(new LocPoint(order.destination, false));
        }
        Collections.sort(points, (o1, o2)->(o1.location - o2.location));
        int count = 0;
        for(LocPoint point : points) {
            count += point.isStart ? 1 : -1;
            if(count > car.availableSeats) return false;
        }
        return true;
    }

    public Map<Car, List<Order>> dispatch(List<Order> orders, IDispatchStrategy strategy) {
        Map<Car, List<Order>> result = new HashMap<>();
        if(orders == null || orders.size() < 1 || strategy == null) {
            return result;
        }
        //sort all orders by departure.
        Collections.sort(orders, (o1, o2)->(o1.departure - o2.departure));

        //determine how may cars we needed.
        int requiredCars = calRequiredCars(orders);

        List<Car> cars = CarManagement.getInstance().getCars(requiredCars, new SortCarByLocation());
        if(cars == null || cars.size() != requiredCars) {
            //do something
            return result;
        }

        return strategy.dispatch(cars, orders);
    }

    // calculate how many cars we need to finish all orders.
    private int calRequiredCars(List<Order> orders) {
        PriorityQueue<Integer> destQueue = new PriorityQueue<>();
        destQueue.offer(orders.get(0).destination);
        for(int i = 1; i < orders.size(); i++) {
            if(orders.get(i).departure >= destQueue.peek()) destQueue.poll();
            destQueue.offer(orders.get(i).destination);
        }
        return destQueue.size();
    }

    public boolean canBeLuckyDriver(List<Order> orders, int target) {
        if(orders == null || orders.size() < 1) return false;
        if(target < 1) return true;

        int[] miles = new int[orders.size()];
        int sum = 0, index = 0;
        for(Order order : orders) {
            miles[index] = order.destination - order.departure;
            sum += miles[index++];
        }

        if(sum < target) return false;
        return luckyHelper(miles, target);

    }

    private boolean luckyHelper(int[] miles, int target) {
        for(int i = 0; i < miles.length; i++) {
            if(miles[i] == 0) continue;
            int tmp = miles[i];
            miles[i] = 0;
            if(tmp >= target || !luckyHelper(miles, target - tmp)) {
                miles[i] = tmp;
                return true;
            }
            miles[i] = tmp;
        }
        return false;
    }
}
