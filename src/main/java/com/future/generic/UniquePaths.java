package com.future.generic;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-paths/
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 * Thoughts:
 * Since we want to find how many unique paths are there, one solution in my mind is back-tracking, try all paths, it's a bottom-up way.
 *
 * If we take a closer look at the back-tracking solution, it's easy to see there are duplicated computation.
 * for example:
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * the cell 5 was computed more than once, both 2 and 4 will compute 5.
 *
 * So the better solution we can use a cache or use DP solution.
 *
 * The DP solution is also straightforward, starts from top-left corner, initial its value to 1 (there's only one way if the start and end are same cell),
 * then for any cell f(m, n) = f(m - 1, n) + f(m, n - 1)
 */
public class UniquePaths {

    /**
     * The back-tracking solution.
     * Time complexity: O((m*n)^2)
     * Space complexity: O(1)
     * @param m
     * @param n
     * @return
     */
    public int backtracking(int m, int n) {
        return helper(m, n, 0, 0);
    }

    private int helper(int m, int n, int r, int c) {
        if(r >= m || c >= n) {
            return 0;
        }

        if(r == m - 1 && c == n - 1) {
            return 1;
        }

        return helper(m, n, r + 1, c) + helper(m, n, r, c + 1);
    }

    /**
     * The back-tracking with cache, the point is finding the duplicated computation and cache it.
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     * @param m
     * @param n
     * @return
     */
    public int backtrackingWithCache(int m, int n) {
        int[][] cache = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                cache[i][j] = -1;
            }
        }
        return helper2(m, n, 0, 0, cache);
    }

    private int helper2(int m, int n, int r, int c, int[][] cache) {
        if(r >= m || c >= n) {
            return 0;
        }

        if(r == m - 1 && c == n - 1) {
            return 1;
        }

        if(cache[r][c] >= 0) {
            return cache[r][c];
        }

        int res = helper2(m, n, r + 1, c, cache) + helper2(m, n, r, c + 1, cache);
        cache[r][c] = res;
        return res;
    }

    /**
     * The DP solution, it's a top-down solution.
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     * @param m
     * @param n
     * @return
     */
    public int db(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] += (i > 0 ? dp[i - 1][j] : 0);
                dp[i][j] += (j > 0 ? dp[i][j - 1] : 0);
            }
        }
        return dp[m - 1][n - 1];
    }
}
