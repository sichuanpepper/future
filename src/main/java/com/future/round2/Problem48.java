package com.future.round2;

/**
 * https://leetcode.com/problems/rotate-image/description/
 *
 * Created by someone on 12/6/17.
 */
public class Problem48 {
    /**
     * It's nothing about algorithm, you just need to know how to rotate a matrix.
     * Swap it symmetrically, and then swap it horizontally.
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length < 1) return;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = i; j < matrix.length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        for(int i = 0; i < matrix.length; i++) {
            int start = 0, end = matrix.length - 1;
            while(start < end) {
                int tmp = matrix[i][start];
                matrix[i][start++] = matrix[i][end];
                matrix[i][end--] = tmp;
            }
        }
    }

}
