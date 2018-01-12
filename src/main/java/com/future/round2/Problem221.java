package com.future.round2;

import com.future.utils.DisplayUtils;

/**
 * https://leetcode.com/problems/maximal-square/description/
 *
 Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 For example, given the following matrix:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0
 Return 4.

 * Created by xingfeiy on 1/4/18.
 */
public class Problem221 {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length < 1) return 0;
        int max = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') continue;
                dp[i][j] = 1;
                if(i > 0 && j > 0 && dp[i - 1][j - 1] > 0) {
                    int step = dp[i - 1][j - 1];
                    int curStep = 1;
                    while (curStep <= step && matrix[i - curStep][j] != '0' && matrix[i][j - curStep] != '0') curStep++;
                    dp[i][j] += (curStep - 1);
                }

                max = Math.max(max, dp[i][j] * dp[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[5][4];
        matrix[0] = new char[]{'0','0','0','1'};
        matrix[1] = new char[]{'1','1','0','1'};
        matrix[2] = new char[]{'1','1','1','1'};
        matrix[3] = new char[]{'0','1','1','1'};
        matrix[4] = new char[]{'0','1','1','1'};
        Problem221 p = new Problem221();
        System.out.println(p.maximalSquare(matrix));
    }
}

