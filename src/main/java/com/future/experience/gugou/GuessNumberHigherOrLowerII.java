package com.future.experience.gugou;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower-ii/
 */
public class GuessNumberHigherOrLowerII {
    /**
     * The problem is minimize the max cost.
     * Start from simple cases:
     * if n = 1, return $0
     * if n = 2,
     *      - pick 1, $1
     *      - pic 2, $2
     *  if n = 3
     *      - pick 1, and then 2, $1 + $2,
     *      - pick 1 then 3, $1 + $3
     *      - pick 2, $2
     *      - pick 3, then 2, $3 + $2
     *      - pick 3, then 1, $3 + $1
     *
     *  So the straightforward way is check the element iteratively
     *  for each element i, the cost = min(1, i - 1) or min(i + 1, n), we should pick the maximum one, then plus i.
     *
     *  it's easy to see there are some sub-problems are computed again and again, so we can use memoization
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 0; i < dp.length; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        return helper(1, n, dp);
    }

    private int helper(int start, int end, int[][] dp) {
        if(start > end) return 0;
        if(start == end) return 0;
        if(dp[start][end] != Integer.MAX_VALUE) {
            return dp[start][end];
        }
        int minCost = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++) {
            minCost = Math.min(minCost, Math.max(helper(start, i - 1, dp) + i, helper(i + 1, end, dp) + i));
        }
        dp[start][end] = minCost;
        return minCost;
    }
}
