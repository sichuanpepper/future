package com.future.round2;

/**
 * https://leetcode.com/problems/01-matrix/description/
 *
 Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

 The distance between two adjacent cells is 1.
 Example 1:
 Input:

 0 0 0
 0 1 0
 0 0 0
 Output:
 0 0 0
 0 1 0
 0 0 0
 Example 2:
 Input:

 0 0 0
 0 1 0
 1 1 1
 Output:
 0 0 0
 0 1 0
 1 2 1
 Note:
 The number of elements of the given matrix will not exceed 10,000.
 There are at least one 0 in the given matrix.
 The cells are adjacent in only four directions: up, down, left and right.

 * Created by xingfeiy on 1/7/18.
 */
public class Problem542 {
    /**
     * Analyze:
     * We can do two pass updates.
     * First pass, from left upper conner to right bottom conner, update current cell based on left and upper cell.
     * Second pass, from right bottom conner to left upper conner, update current cell based on right and under cell.
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) continue;
                matrix[i][j] = 10; // You can use any integer bigger than 1 except Integer.MAX_VALUE, overflow.
                if(i > 0) matrix[i][j] = Math.min(matrix[i - 1][j] + 1, matrix[i][j]);
                if(j > 0) matrix[i][j] = Math.min(matrix[i][j - 1] + 1, matrix[i][j]);
            }
        }

        for(int i = matrix.length - 1; i >= 0; i--) {
            for(int j = matrix[0].length - 1; j >= 0; j--) {
                if(i < matrix.length - 1) matrix[i][j] = Math.min(matrix[i + 1][j] + 1, matrix[i][j]);
                if(j < matrix[0].length - 1) matrix[i][j] = Math.min(matrix[i][j + 1] + 1, matrix[i][j]);
            }
        }
        return matrix;
    }
}
