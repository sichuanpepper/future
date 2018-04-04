package com.future.round2;

/**
 * https://leetcode.com/problems/gas-station/description/

 There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

 You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).

 You begin the journey with an empty tank at one of the gas stations.

 Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

 Note:
 The solution is guaranteed to be unique.
 * Created by xingfeiy on 1/11/18.
 */
public class Problem134 {
    /**
     * Analyze:
     * 1. If total gas is equal or greater than total cost, there must be a way to travel around the circuit once.
     * 2. From start station(initial as first one), once the gas in tank is less than 0, that means any station in range
     *    from start to current station that can't be the final starting station, since no one can pass current station in that range.
     *    It also means, if that starting station existed, the position must be after the current one.
     *
     * Prove:
     *  1. Let's say there are three stations A, B and C,
     *      - If gas(A) - cost(A) + gas(B) - cost(B) + gas(C) - cost(C) >= 0, then A is the starting.
     *      - If gas(B) - cost(B) + gas(C) - cost(C) + gas(A) - cost(A) >= 0, then B is the starting.
     *      - If gas(C) - cost(C) + gas(A) - cost(A) + gas(B) - cost(B) >= 0, then C is the starting.
     *  It's easy to see, the above formulas can be transform to a same formula gas(A)+gas(B)+gas(C) >= cost(A)+cost(B)+cost(C)
     *  That means, if this formula satisfied, there must be a solution, either A or B or C.
     *  It also means, if this formula doesn't satisfy, there's no solution.
     *
     *  2. Let's say there are stations A, B, C, if A can't reach C, that means B also can't reach C.
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || cost == null || gas.length != cost.length) return -1;
        int start = 0, tank = 0, diff = 0;
        for(int i = 0; i < cost.length; i++) {
            tank = tank + gas[i] - cost[i];
            diff += (gas[i] - cost[i]);
            if(tank < 0) { //can't move to next station
                start = i + 1;
                tank = 0;
            }
        }
        return diff >= 0 ? start : -1;
    }
}
