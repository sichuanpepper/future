package com.future.round2;

/**
 https://leetcode.com/problems/set-matrix-zeroes/description/

 Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.


 * Created by xingfeiy on 1/6/18.
 */
public class Problem73 {
    /**
     * Analyze:
     * The straightforward way is go through the matrix and store the status of each row and each column, but it's required
     * space complexity O(m + n)
     *
     * We can use the first slot of each row to store the status of current row, 0 represents contains 0, and same thing,
     * We can use the first slot of each column to store the status of current column.
     * But there's a slot which shared with row and column, the first slot, so we use this slot reprents the status of row 0
     * and use additional variable represents the status of column 0.
     *
     * Basically, two pass are required, set the status in first pass and set the others in second pass.
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length < 1) return;
        boolean firstColHasZero = false;
        //first pass
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] == 0) firstColHasZero = true;
            for(int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        //second pass
        for(int i = matrix.length - 1; i >= 0; i--) {
            for(int j = matrix[0].length - 1; j > 0; j--) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
            //don't forget the status of first column.
            if(firstColHasZero) matrix[i][0] = 0;
        }
    }
}
