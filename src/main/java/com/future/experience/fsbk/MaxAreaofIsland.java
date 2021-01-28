package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/max-area-of-island/
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 */
public class MaxAreaofIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length < 1) return 0;
        int max = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) continue;
                max = Math.max(max, helper(grid, i, j));
            }
        }
        return max;
    }


    private int helper(int[][] grid, int row, int col) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return 0;
        if(grid[row][col] == 0) return 0;
        grid[row][col] = 0;
        int sum = 1;
        sum += helper(grid, row - 1, col);
        sum += helper(grid, row + 1, col);
        sum += helper(grid, row, col - 1);
        sum += helper(grid, row, col + 1);
        return sum;
    }
}
