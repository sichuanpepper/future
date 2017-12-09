package com.future.round2;

/**
 * https://leetcode.com/problems/n-queens-ii/description/
 *
 * Follow up for N-Queens problem.

 Now, instead outputting board configurations, return the total number of distinct solutions.

 * Created by xingfeiy on 12/8/17.
 */
public class Problem52 {
    /**
     * Analyze:
     * It's a follow up question of problem 51, we are asked output all distinct solutions in problem 51, and here we just
     * need to output the number of distinct solutions, it's a little bit easier than problem 51.
     *
     * We still use back tracking algorithm here.
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        if(n < 1) return 0;
        helper(new boolean[n][n], 0);
        return count;
    }

    private int count = 0;
    private void helper(boolean[][] board, int col) {
        if(col >= board.length) {
            count++;
            return;
        }

        for(int i = 0; i < board.length; i++) {
            if(attacked(board, i, col)) continue;
            board[i][col] = true;
            helper(board, col + 1);
            board[i][col] = false;
        }
    }

    private boolean attacked(boolean[][] board, int row, int col) {
        for(int i = 0; i < board.length; i++) {
            if(board[row][i] || board[i][col]) return true;
        }

        int tmpRow = row, tmpCol = col;
        while (tmpRow >= 0 && tmpCol>= 0) {
            if(board[tmpRow--][tmpCol--]) return true;
        }

        tmpRow = row;
        tmpCol = col;
        while (tmpRow < board.length && tmpCol >= 0) {
            if(board[tmpRow++][tmpCol--]) return true;
        }

        while (row >=0 && col < board.length) {
            if(board[row--][col++]) return true;
        }
        return false;
    }
}
