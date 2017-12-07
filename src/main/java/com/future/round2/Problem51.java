package com.future.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/description/
 *
 * Created by someone on 12/6/17.
 */
public class Problem51 {
    /**
     * Analyze:
     * The N-Queen is a back tracking proble.
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if(n < 1) return res;
        //init board.
        char[][] board = new char[n][n];
        for(char[] row : board) Arrays.fill(row, '.');
        helper(board, 0, res, n);
        return res;
    }

    private void helper(char[][] board, int curCol, List<List<String>> res, int n) {
        if(curCol >= n) {
            //found it.
            res.add(toStringList(board));
            return;
        }

        for(int i = 0; i < n; i++) {
            if(attacked(board, i, curCol)) continue;
            //place queen on (i, curCol)
            board[i][curCol] = 'Q';
            helper(board, curCol + 1, res, n);
            board[i][curCol] = '.';
        }
    }

    private boolean attacked(char[][] board, int row, int col) {
        for(int i = 0; i < board.length; i++) {
            if(board[row][i] == 'Q') return true;
            if(board[i][col] == 'Q') return true;
        }
        int tmpRow = row;
        int tmpCol = col;
        while(tmpRow >=0 && tmpCol >=0) {
            if(board[tmpRow--][tmpCol--] == 'Q') return true;
        }

        tmpRow = row;
        tmpCol = col;
        while(tmpRow < board.length && tmpCol >= 0) {
            if(board[tmpRow++][tmpCol--] == 'Q') return true;
        }

        while(row >=0 && col < board.length) {
            if(board[row--][col++] == 'Q') return true;
        }
        return false;
    }

    private List<String> toStringList(char[][] board) {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            String row = "";
            for(int j = 0; j < board.length; j++) {
                row += board[i][j];
            }
            res.add(row);
        }
        return res;
    }

    public static void main(String[] args) {
        Problem51 p = new Problem51();
        p.solveNQueens(4);
    }
}
