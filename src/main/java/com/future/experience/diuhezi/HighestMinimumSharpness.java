package com.future.experience.diuhezi;

/**
 * Created by xingfeiy on 7/9/18.
 */
public class HighestMinimumSharpness {
    // Given a 2-d array of "sharpness" values. Find a path from the leftmost column to the rightmost column which has
    // the highest minimum sharpness.
    // Output the highest minimum sharpness. Each move can only move to the top right, right or bottom right grid.
    // Example: 3*3 matrix
    // 5 7 2
    // 7 5 8
    // 9 1 5
    // The path with highest minimum sharpness is 7-->7-->8, because 7 is the highest minimum value in all the paths.
    // Idea: Use DP dp[r][c] = min(max(dp[r-1][c-1], dp[r][c-1], dp[r+1][c-1]), grid[r][c])
    public int highestMinVal(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n]; // dp stores the highest min value of the path at current position

        for (int r = 0; r < m; r++)
            // fill the col 0 of dp
            dp[r][0] = grid[r][0];

        // Iterate from col 1, fill dp
        for (int c = 1; c < n; c++) {
            for (int r = 0; r < m; r++) {
                int max = 0;
                for (int i = Math.max(0, r - 1); i <= Math.min(m - 1, r + 1); i++) {
                    max = Math.max(max, dp[i][c - 1]);
                }

                // update the highest min value on current position
                dp[r][c] = Math.min(max, grid[r][c]);
            }
        }

        // Find the highest min value of every path
        int res = 0;
        for (int r = 0; r < m; r++)
            res = Math.max(res, dp[r][n - 1]);

        return res;
    }

    /**
     * The problem actually is, from left to right, for each grid, there are at most 3 directions to move.
     * First, find the minimum value in each path.
     * Second, find the maximum value among these minimum values.
     *
     * @param grid
     * @return
     */
    public int highestMinValMy(int[][] grid) {
        if(grid == null || grid.length < 1) return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        for(int i = 0; i < dp.length; i++) dp[i][0] = grid[i][0];

        for(int col = 1; col < grid[0].length; col++) {
            for(int row = 0; row < grid.length; row++) {
                int leftUpper = row > 0 ? dp[row - 1][col - 1] : Integer.MIN_VALUE;
                int leftLower = row < grid.length - 1 ? dp[row + 1][col - 1] : Integer.MIN_VALUE;
                dp[row][col] = Math.max(Math.max(leftLower, leftUpper), dp[row][col - 1]);
                dp[row][col] = Math.min(grid[row][col], dp[row][col]);
            }
        }
        int res = Integer.MIN_VALUE;
        for(int row = 0; row < dp.length; row++) res = Math.max(dp[row][dp[0].length - 1], res);
        return res;
    }

    public static void main(String[] args) {
        HighestMinimumSharpness so = new HighestMinimumSharpness();

        int[][] grid = new int[][]{
                {5, 7, 2},
                {7, 5, 8},
                {9, 1, 5}
        };

        assert so.highestMinVal(grid) == 7;

        System.out.println(so.highestMinValMy(grid));
        grid = new int[][]{
                {5, 7, 2},
                {7, 5, 8},
                {9, 9, 5}
        };
        System.out.println(so.highestMinValMy(grid));
        System.out.println("Success!");
    }
}
