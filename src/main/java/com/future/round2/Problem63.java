package com.future.round2;

/**
 * https://leetcode.com/problems/unique-paths-ii/description/
 *
 * Follow up for "Unique Paths":

 Now consider if some obstacles are added to the grids. How many unique paths would there be?

 An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 For example,
 There is one obstacle in the middle of a 3x3 grid as illustrated below.

 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 The total number of unique paths is 2.

 Note: m and n will be at most 100.

 * Created by xingfeiy on 12/9/17.
 */
public class Problem63 {
    /**
     * Analyze:
     * It's similar with problem 62.
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length < 1 || obstacleGrid[0][0] == 1) return 0;

        for(int i = 0; i < obstacleGrid.length; i++) {
            for(int j = 0; j < obstacleGrid[0].length; j++) {
                if(i == 0 && j == 0) {
                    obstacleGrid[i][j] = 1;
                    continue;
                }

                if(obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                    continue;
                }
                obstacleGrid[i][j] = ((i > 0) ? obstacleGrid[i - 1][j] : 0) + ((j > 0) ? obstacleGrid[i][j - 1] : 0);
            }
        }
        return obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
}
