package com.future.round2;

/**
 * https://leetcode.com/problems/unique-paths/description/
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?
 *
 * Created by xingfeiy on 12/9/17.
 */
public class Problem62 {
    /**
     * Analyze:
     * It's a typical 2D DP problem.
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if(m < 1 || n < 1) return 0;
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1;
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }
}
