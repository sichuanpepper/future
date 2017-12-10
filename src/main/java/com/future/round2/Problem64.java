package com.future.round2;

/**
 * https://leetcode.com/problems/minimum-path-sum/description/
 *
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.

 Example 1:
 [[1,3,1],
 [1,5,1],
 [4,2,1]]
 Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
 * Created by xingfeiy on 12/9/17.
 */
public class Problem64 {
    /**
     * It's same issue as problem 63
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length < 1) return Integer.MIN_VALUE;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                grid[i][j] += Math.min((i > 0 ? grid[i - 1][j] : Integer.MAX_VALUE),(j > 0 ? grid[i][j - 1] : Integer.MAX_VALUE));
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
