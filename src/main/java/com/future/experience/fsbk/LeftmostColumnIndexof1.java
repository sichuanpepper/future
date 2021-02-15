package com.future.experience.fsbk;

import java.util.Arrays;

/**
 * https://leetcode.com/discuss/interview-question/341247/Facebook-or-Phone-screen-or-Leftmost-column-index-of-1
 * In a binary matrix (all elements are 0 and 1), every row is sorted in ascending order (0 to the left of 1). Find the leftmost column index with a 1 in it.
 *
 * Example 1:
 *
 * Input:
 * [[0, 0, 0, 1],
 *  [0, 0, 1, 1],
 *  [0, 1, 1, 1],
 *  [0, 0, 0, 0]]
 * Output: 1
 * Example 2:
 *
 * Input:
 * [[0, 0, 0, 0],
 *  [0, 0, 0, 0],
 *  [0, 0, 0, 0],
 *  [0, 0, 0, 0]]
 * Output: -1
 * Expected solution better than O(r * c).
 *
 * Solution
 */
public class LeftmostColumnIndexof1 {
    public static int solution1(int[][] matrix) {
        if(matrix == null) return 0;
        int row = 0, col = matrix[0].length - 1;
        int candidate = 0;
        while (row <= matrix.length - 1 && col >= 0) {
            if(matrix[row][col] == 1) {
                candidate = col;
                col--;
            } else {
                row++;
            }
        }
        return candidate;
    }



    public static int solution2(int[][] matrix) {
        if(matrix == null) return 0;
        int candidate = 0;
        for(int row = 0; row < matrix.length; row++) {
            int first = findFirst(matrix[row], 1);
            if(first >= 0) candidate = first;
        }
        return candidate;
    }

    private static int findFirst(int[] array, int target) {
        int start = 0, end = array.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(array[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(array[start] == target) return start;
        return array[end] == target ? end : - 1;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 0, 1},
                          {0, 0, 1, 1},
                          {0, 1, 1, 1},
                          {0, 0, 0, 0}};
        System.out.println(solution1(matrix));
        System.out.println(solution2(matrix));

        int[][] matrix2 = {{0, 0, 0, 0},
                           {0, 0, 0, 0},
                           {0, 0, 0, 0},
                           {0, 0, 0, 0}};
        System.out.println(solution1(matrix2));
        System.out.println(solution2(matrix2));

        int[][] matrix3 = {{0, 0, 0, 0},
                           {0, 0, 0, 0},
                           {0, 0, 0, 0},
                           {0, 0, 0, 1}};
        System.out.println(solution1(matrix3));
        System.out.println(solution2(matrix3));
    }
}
