package com.future.foundation.algo.backtracking;

import com.future.utils.DisplayUtils;

import java.util.List;

/**
 * Created by someone on 7/20/17.
 */
public class EightQueen {
    //we have n * n chessboard, and we want to place n queens.
    public List<int[][]> findAll(int n) {
        int[][] board = new int[n][n];
        helper(board, 0);
        return null;
    }

    private void helper(int[][] board, int start) {
        if(start == board.length) {
            DisplayUtils.printTwoDimensionsArray(board);
            System.out.println("=============================");
            return;
        }

        for(int i = 0; i < board.length; i++) {
            if(isSafe(board, start, i)) {
                board[start][i] = 1;
                helper(board, start + 1);
                board[start][i] = 0;
            }
        }
    }

    private boolean isSafe(int[][] board, int r, int c) {
        //row and column safe
        for(int i = 0; i < board.length; i++) {
            if(board[r][i] == 1) {
                return false;
            }

            if(board[i][c] == 1) {
                return false;
            }
        }

        //
        int i = r;
        int j = c;
        while (i >= 0 && j>= 0) {
            if(board[i--][j--] == 1) {
                return false;
            }
        }

        i = r;
        j = c;
        while (i < board.length && j < board.length) {
            if(board[i++][j++] == 1) {
                return false;
            }
        }

        i = r;
        j = c;
        while (i >= 0 && j < board.length) {
            if(board[i--][j++] == 1) {
                return false;
            }
        }

        i = r;
        j = c;
        while (i < board.length && j >= 0) {
            if(board[i++][j--] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new EightQueen().findAll(8);
    }
}
