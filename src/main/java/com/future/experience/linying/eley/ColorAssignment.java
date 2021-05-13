package com.future.experience.linying.eley;

/**
 * In this problem, we have `m` cakes placed in a row on a baking sheet - eg. Cake 1, Cake 2, Cake 3, ....
 *
 * - We'd like to decorate each cake either red, green, or blue.
 * - For each combination of cake and color, we are given the amount of money it costs to decorate that cake with that color.
 * - A cake cannot be decorated the same color as its neighbor cakes (left and right)
 * - The goal is to decorate all of the cakes for the minimum total cost.
 *
 *
 * https://www.lintcode.com/problem/paint-house/
 *
 * Thoughts:
 * - It's an optimal problem.
 * - It's easy to see optimal substructure here.
 * f(m) = min{f(m - 1, c1), f(m - 1, c2), f(m - 1, c3)}
 */
public class ColorAssignment {
    public int minCost(int[][] costs) {
        if(costs == null || costs.length < 1) {
            return 0;
        }
        // write your code here
        int[] dp = new int[3];
        dp[0] = costs[0][0];
        dp[1] = costs[0][1];
        dp[2] = costs[0][2];
        for(int i = 1; i < costs.length; i++) {
            int[] tmp = new int[3];
            tmp[0] = Math.min(dp[1], dp[2]) + costs[i][0];
            tmp[1] = Math.min(dp[0], dp[2]) + costs[i][1];
            tmp[2] = Math.min(dp[0], dp[1]) + costs[i][2];
            dp = tmp;
        }
        return Math.min(Math.min(dp[1], dp[2]), dp[0]);
    }
}
