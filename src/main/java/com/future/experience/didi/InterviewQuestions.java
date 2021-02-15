package com.future.experience.didi;

import java.util.*;

public class InterviewQuestions {
    /**
     * The car can take only 1 passenger
     * You are guaranteed each order is always valid.
     * Time complexity: O(nlg(n))
     * Space complexity: O(1)
     * @param orders
     * @return
     */
    public boolean canFinish(int[][] orders) {
        if(orders == null || orders.length < 2) return true;
        Arrays.sort(orders, (o1, o2) -> (o1[0] - o2[0]));

        for(int i = 1; i < orders.length; i++) {
            if(orders[i][0] < orders[i - 1][1]) return false;
        }
        return true;
    }

    /**
     * Given a list of orders and the maximum number of passengers the car can take.
     * If the driver is not able to finish all orders, return an empty range, otherwise find all available ranges.
     * Available means the car is not full yet, can take at lease 1 passenger.
     * You are guaranteed the given orders is not null.
     *
     * For example, the car can take at most 2 passengers, and we are given orders:
     * 1-8, 2-6, 8-10
     * return 1-2, 6-10 (merge is required for the consecutive ranges)
     * @param orders
     * @param n
     * @return
     */
    public List<Integer[]> findAllAvailableRanges(int[][] orders, int n) {
        List<StationPoint> sPoints = new ArrayList<>();
        for(int[] order : orders) {
            sPoints.add(new StationPoint(order[0], true));
            sPoints.add(new StationPoint(order[1], false));
        }
        Collections.sort(sPoints, (o1, o2)->(o1.station - o2.station));
        int countOfStart = 0, start = -1;
        List<Integer[]> res = new ArrayList<>();
        for(StationPoint point : sPoints) {
            countOfStart += point.isStart ? 1 : -1;
            if(countOfStart > n) {
                return new ArrayList<>();
            } else if(countOfStart < n && start == -1) {
                start = point.station;
            } else if(countOfStart == n && start !=  -1) {
                res.add(new Integer[]{start, point.station});
                start = -1;
            }
        }
        if(start != -1) res.add(new Integer[]{start, sPoints.get(sPoints.size() - 1).station});

        return res;
    }


    /**
     * The car can take at most n passengers.
     * Time complexity: O(nlg(n))
     * Space complexity: O(n)
     * @param orders
     * @param n
     * @return
     */
    public boolean canFinish(int[][] orders, int n) {
        if(orders == null || orders.length <= n) return true;
        List<StationPoint> sPoints = new ArrayList<>();
        for(int[] order : orders) {
            sPoints.add(new StationPoint(order[0], true));
            sPoints.add(new StationPoint(order[1], false));
        }
        Collections.sort(sPoints, (o1, o2)->{
            if(o1.station == o2.station) {
                return o1.isStart ? 1 : -1;
            }
            return o1.station - o2.station;
        });

        int countOfStart = 0;
        for(StationPoint point : sPoints) {
            countOfStart += point.isStart ? 1 : -1;
            if(countOfStart > n) return false;
        }
        return true;
    }

    private class StationPoint {
        public int station;
        public boolean isStart;

        public StationPoint(int station, boolean isStart) {
            this.station = station;
            this.isStart = isStart;
        }
    }

    /**
     * The minimum number of drivers required
     * Time complexity: O(nlg(n))
     * Space complexity: O(n)
     * @param orders
     * @return
     */
    public int requiredDrivers(int[][] orders) {
        if(orders == null) return 0;
        Arrays.sort(orders, (o1, o2) -> (o1[0] - o2[0]));

        PriorityQueue<Integer> endQueue = new PriorityQueue<>();
        endQueue.offer(orders[0][1]);
        for(int i = 1; i < orders.length; i++) {
            if(orders[i][0] >= endQueue.peek()) endQueue.poll();
            endQueue.offer(orders[i][1]);
        }
        return endQueue.size();
    }

    /**
     * Given a list of pickup points which represent by a list of integers.
     * For each pickup point, there may be zero , one or more riders, and each rider may head to same or different destination.
     *
     * As a DiDi driver, your responsibility is taking orders and send the riders to their destinations.
     * Assume we knew the price of each order in advance, and
     *  - Each order consists of departure, destination and cost, like [2, 4, 100] means the cost from 2 to 4 is $100
     *  - You are allowed to take at most n orders each day.
     *  - You are starting from an initial location.
     *  - Your business is end today if you went to a location where no rider is waiting over there.
     *
     * Find the maximum profit you can make today.
     *
     * example:
     * you are starting from location 1.
     * maximum orders allowed: 3
     * orders: [1,2,100],[1,3,150],[1,4,200], [2,3,100], [3,5,50], [2,5,200]
     *
     * The maximum profit you can make is $300, 1->2->5
     *
     * @param src
     * @param orders
     * @return
     */
    public int maxProfit(int src, int[][] orders) {
        return 0;
    }




    public static void main(String[] args) {
        InterviewQuestions q = new InterviewQuestions();
        int[][] orders = new int[][]{new int[]{0, 2}, new int[]{2, 3}, new int[]{4, 6}};
        System.out.println(q.canFinish(orders));  //true
        System.out.println(q.requiredDrivers(orders));
        System.out.println("If the car can take 2 passengers: " + q.canFinish(orders, 2));

        orders = new int[][]{new int[]{0, 2}, new int[]{2, 5}, new int[]{4, 6}};
        System.out.println(q.canFinish(orders));  //false
        System.out.println(q.requiredDrivers(orders));
        System.out.println("If the car can take 1 passengers: " + q.canFinish(orders, 1));
        System.out.println("If the car can take 2 passengers: " + q.canFinish(orders, 2));

        orders = new int[][]{new int[]{0, 8}, new int[]{2, 3}, new int[]{4, 6}};
        System.out.println(q.canFinish(orders));  //false
        System.out.println(q.requiredDrivers(orders));
        System.out.println("If the car can take 3 passengers: " + q.canFinish(orders, 3));

        orders = new int[][]{new int[]{0, 8}, new int[]{2, 3}, new int[]{4, 6}, new int[]{1, 5}, new int[]{2, 10}, new int[]{10, 12}};
        System.out.println(q.canFinish(orders));  //false
        System.out.println(q.requiredDrivers(orders));
        System.out.println("If the car can take 3 passengers: " + q.canFinish(orders, 3));
        System.out.println("If the car can take 4 passengers: " + q.canFinish(orders, 4));

        System.out.println("Find all available ranges");
        List<Integer[]> res = q.findAllAvailableRanges(orders, 4);
        for(Integer[] range : res) {
            System.out.println(range[0] + " to " + range[1]);
        }
    }
}
