package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.*;

/**
 * https://leetcode.com/problems/sudoku-solver/description/
 *
 * Created by xingfeiy on 12/22/17.
 */
public class Problem37 {

    /**
     * Analyze:
     * 1. Each cell can be filled in one number from 1 to 9
     * 2. Check if each number is valid.
     *
     * Back tracking.
     * @param board
     */
    public void solveSudoku(char[][] board) {
//        helper(board, 0, 0);
    }



    public static void main(String[] args) {
        char[][] board = new char[9][9];

        board[0] = new char[]{'.','.','.','.','.','.','.','.','2'};
        board[1] = new char[]{'.','.','.','.','.','.','6','.','.'};
        board[2] = new char[]{'.','.','1','4','.','.','8','.','.'};
        board[3] = new char[]{'.','.','.','.','.','.','.','.','.'};
        board[4] = new char[]{'.','.','.','.','.','.','.','.','.'};
        board[5] = new char[]{'.','.','.','.','3','.','.','.','.'};
        board[6] = new char[]{'5','.','8','6','.','.','.','.','.'};
        board[7] = new char[]{'.','9','.','.','.','.','4','.','.'};
        board[8] = new char[]{'.','.','.','.','5','.','.','.','.'};
        Problem37 p = new Problem37();
        p.solveSudoku(board);
        DisplayUtils.printTwoDimensionsArray(board);
    }
}
