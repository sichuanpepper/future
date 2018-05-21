package com.future.round2;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 *
 * Created by xingfeiy on 5/19/18.
 */
public class Problem74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) return false;
        int start = 0, end = matrix.length * matrix[0].length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            int midVal = getValue(matrix, mid);
            if(midVal == target) {
                return true;
            } else if(midVal > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return (getValue(matrix, start) == target) || (getValue(matrix, end) == target);
    }

    private int getValue(int[][] matrix, int k) {
        int row = k / matrix[0].length;
        int col = k % matrix[0].length;
        return matrix[row][col];
    }
}
