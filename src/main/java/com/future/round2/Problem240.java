package com.future.round2;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 *
 *
 * Created by someone on 11/7/17.
 */
public class Problem240 {
    /**
     * Analyze:
     * Two pointers, from the left bottom corn
     * if cur_val > given_val row--
     * else col++
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1) {
            return false;
        }

        int row = matrix.length - 1, col = 0;
        while (row >= 0 && col < matrix[0].length) {
            int val = matrix[row][col];
            if(matrix[row][col] == target) {
                return true;
            } else if(matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array = new int[5][5];
        array[0] = new int[]{1,4,7,11,15};
        array[1] = new int[]{2,5,8,12,19};
        array[2] = new int[]{3,6,9,16,22};
        array[3] = new int[]{10,13,14,17,24};
        array[4] = new int[]{18,21,23,26,30};
        System.out.println(searchMatrix(array, 5));

    }
}
