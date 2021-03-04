package com.future.experience.fsbk;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/two-city-scheduling/
 *
 * Analyze:
 * each person has option to go to City A or city B, we can use backtracking to solve it, but it's not a good solution,.
 *
 * Solution:
 * Assume we are going to send all people (2*n) to city A first, we can get a cost(sum costs[i][0]), that's the money we need to pay.
 * But after a while, we decide to send half people to city B, we have the change the ticket for half people,
 * for each change, there's a difference price, refund or pay more extra money.
 * To minimize the costs, we have to maximize the refund.
 */
public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        int cost = 0,n = costs.length / 2;
        int[] refund = new int[costs.length];
        for(int i = 0; i < costs.length; i++) {
            cost += costs[i][0];  //the cost of sending all people to city A
            refund[i] = costs[i][1] - costs[i][0];  // the refund of change ticket.
        }
        Arrays.sort(refund);
        for(int i = 0; i < n; i++) {
            cost += refund[i];
        }
        return cost;
    }
}
