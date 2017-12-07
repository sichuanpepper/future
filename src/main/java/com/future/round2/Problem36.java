package com.future.round2;

/**
 * https://leetcode.com/problems/valid-sudoku/description/
 *
 * Created by someone on 12/3/17.
 */
public class Problem36 {
    /**
     * Analyze:
     * We can list the fail test cases first.
     * 1. Same value has same x or y value, means in same row or same column.
     * 2. Same value in same diagonal.
     * 3. Same value in same minimum 3*3 cell, which means x1 - x2 < 3 and y1 - y2 < 3
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        return true;
    }
}
