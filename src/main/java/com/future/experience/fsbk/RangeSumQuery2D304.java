package com.future.experience.fsbk;

/*8
https://leetcode.com/problems/range-sum-query-2d-immutable/

2, 0, 1
1, 0, 1
0, 3, 0

For any given rect (r1, c1, r2, c2),
 */
public class RangeSumQuery2D304 {
    private int[][] sum;

    public RangeSumQuery2D304(int[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        sum = new int[m + 1][n + 1];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                sum[i + 1][j + 1] = matrix[i][j] + sum[i + 1][j] + sum[i][j + 1] - sum[i][j];
            }
        }
    }

    /**
     2, 2, 3
     3, 3, 5 (left + upper - top-left)
     3, 6, 8 (5 + 6 - 3)
     **/
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(sum == null) {
            return 0;
        }
        return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
    }
}
