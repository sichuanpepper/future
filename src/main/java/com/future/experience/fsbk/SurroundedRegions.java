package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/surrounded-regions/
 */
public class SurroundedRegions {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        for(int i = 0; i < n; i++) {
            if(board[0][i] == 'O') {
                helper(board, 0, i);
            }
            if(board[m - 1][i] == 'O') {
                helper(board, m - 1, i);
            }
        }

        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                helper(board, i, 0);
            }
            if(board[i][n - 1] == 'O') {
                helper(board, i, n - 1);
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'T') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }

            }
        }
    }

    private void helper(char[][] board, int row, int col) {
        if(row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1) {
            return;
        }
        if(board[row][col] != 'O') {
            return;
        }
        board[row][col] = 'T';
        helper(board, row - 1, col);
        helper(board, row + 1, col);
        helper(board, row, col - 1);
        helper(board, row, col + 1);
    }
}
