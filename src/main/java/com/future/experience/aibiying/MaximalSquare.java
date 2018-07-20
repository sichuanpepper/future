package com.future.experience.aibiying;

/**
 Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 Example:

 Input:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0

 Output: 4

 * Created by xingfeiy on 6/21/18.
 */
public class MaximalSquare {
    /**
     * Analyze:
     * f(m, n) = f(m-1, n-1) + 1
     *  - if (res = f(m-1, n-1)) res > 0 &&
     *  - matrix[m ~ m - res, n] == 1 && matrix[m, n ~ n - res] == 1
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length < 1) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '0') continue;
                if(i > 0 && j > 0 && dp[i - 1][j - 1] > 0) {
                    int pre = dp[i - 1][j - 1];
                    int cur = 1;
                    while (cur <= pre && matrix[i - cur][j] == '1' && matrix[i][j - cur] == '1') cur++;
//                    dp[i][j] += (cur -1);
                    dp[i][j] = cur;
                }
                max = Math.max(max, dp[i][j] * dp[i][j]);
            }
        }
        return max;
    }
}
