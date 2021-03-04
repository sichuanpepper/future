package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/making-a-large-island/
 *
 */
public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        int max = 0;
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid.length; col++) {
                if(grid[row][col] == 0) {
                    grid[row][col] = 1;
                    int res = findOne(grid, row, col, new boolean[grid.length][grid[0].length]);
                    max = Math.max(max, res);
                    grid[row][col] = 0;
                }
            }
        }
        return max == 0 ? grid.length * grid[0].length : max;
    }

    private int findOne(int[][] grid, int row, int col, boolean[][] visited) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return 0;
        }
        if(grid[row][col] != 1 || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        return findOne(grid, row - 1, col, visited) + findOne(grid, row + 1, col, visited)
                + findOne(grid, row, col - 1, visited) + findOne(grid, row, col + 1, visited) + 1;
    }
}
