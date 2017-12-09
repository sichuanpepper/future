package com.future.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/description/
 *
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 For example,
 Given the following matrix:

 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 You should return [1,2,3,6,9,8,7,4,5].

 * Created by xingfeiy on 12/8/17.
 */
public class Problem54 {
    /**
     * Analyze:
     * We can traversal the outermost first, and then remove outermost and do same thing recursively util all elements
     * have been traversed.
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length < 1) return res;
        helper(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, res);
        return res;
    }

    private void helper(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd, List<Integer> res) {
        if(rowStart > rowEnd || colStart > colEnd) return;
        if(rowStart == rowEnd && colStart == colEnd) {
            res.add(matrix[rowStart][colStart]);
            return;
        }

        for(int i = colStart; i <= colEnd; i++) {
            res.add(matrix[rowStart][i]);
        }

        for(int i = rowStart + 1; i <= rowEnd; i++) {
            res.add(matrix[i][colEnd]);
        }

        if(rowStart != rowEnd) {
            for(int i = colEnd - 1; i >= colStart; i--) {
                res.add(matrix[rowEnd][i]);
            }
        }


        if(colStart != colEnd) {
            for(int i = rowEnd - 1; i > rowStart; i--) {
                res.add(matrix[i][colStart]);
            }
        }

        helper(matrix, rowStart + 1, rowEnd - 1, colStart + 1, colEnd - 1, res);

    }
}
