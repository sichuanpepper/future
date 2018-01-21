package com.future.foundation.db;

import com.future.utils.DisplayUtils;

/**
 * Created by someone on 5/24/17.
 */
public class Knapsack {
    //the weight of each stone
    private static int[] stones = new int[]{2, 3, 7};

    private static int[] values =  new int[]{5, 3, 6};

    private static int capacity = 11;

    /**
     * Analyze:
     *
     * Is it an optimal problem? Yes, it is, the problem is find the maximum value solution in all solutions.
     *
     * has optimal substructure?
     * We say that a problem exhibits optimal substructure if an optimal solution to the problem
     * contains within it optimal solutions to subproblems.
     *
     * It has, let's use max(n) to represent the maximum value we can get first, now let's see how to get the subproblem,
     * it's easy to see the capacity is not same in subproblems, so we have to use two-dimensions to describe this problem.
     * Compare to the problem maximum subarray and see the difference.
     *
     * so we use max_value(k, c) to describe this problem, the maximum value in 0...kth elements with capacity c limited.
     *
     * the max_value(k, c) comes from two ways, one, pick up the kth element, the total value could be max_value(k - 1, c - )
     *
     * has overlapping subproblems?
     *
     * For sure, the optimal solution of k which depends on all optimal solutions of 0...k-?.
     *
     * pick up kth or not?
     * max_value(k, c) =
     *
     *
     * @return
     */
    public static int maxValue() {
        int[][] results = new int[stones.length + 1][values.length + 1];
        for(int i = 1; i < stones.length + 1; i++) {
            for(int j = 1; j < capacity + 1; j++) {
                results[i][j] = Integer.MIN_VALUE;
            }
        }

        for(int i = 1; i < stones.length + 1; i++) {
            for(int j = 1; j < capacity + 1; j++) {
                if(stones[i - 1] <= j) {}
            }
        }
        return 0;

    }

    public static int maxValue2() {
        //the problem is:
        // we are given 1, a capacity of bag; 2, the weigh array for each stone; 3, the values array for each stone.
        // we are going to find the max profit with limited bag.
        // that means, find the max profit in m stones with limited bag.
        // so, f(m, c) is it, it's two-dimensions status.
        int[][] maxValue = new int[stones.length + 1][capacity + 1];
        for(int i = 1; i < stones.length + 1; i++) {
            for(int j = 1; j < capacity + 1; j++) {

                if(j >= stones[i - 1]) {
                    maxValue[i][j] = Math.max(maxValue[i - 1][j - stones[i - 1]] + values[i - 1], maxValue[i - 1][j]);
                } else {
                    maxValue[i][j] = maxValue[i - 1][j];
                }
            }
        }

        DisplayUtils.printTwoDimensionsArray(maxValue);

        return maxValue[stones.length][capacity];
    }

    public static int recursive(int[] stones, int[] values, int capacity) {
        if(capacity < 1) return 0;
        int maxValue = 0;
        for(int i = 0; i < stones.length; i++) {
            if(stones[i] <= capacity)
                maxValue = Math.max(maxValue, recursive(stones, values, capacity - stones[i]) + values[i]);
        }
        return maxValue;
    }

    public static void main(String[] args) {
        System.out.println(maxValue2());
        System.out.println(recursive(stones, values, capacity));
    }
}
