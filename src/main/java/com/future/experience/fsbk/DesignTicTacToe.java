package com.future.experience.fsbk;

import java.util.Map;

/**
 * Design a Tic-tac-toe game that is played between two players on anxngrid.
 *
 * You may assume the following rules:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Example:
 *
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * Follow up:
 * Could you do better than O(n2) permove()operation?
 *
 * Thoughts:
 * - The brute force solution is checking row, col and diagonal for each move.
 *
 * Optimal solution:
 * - We actually need to know the count of horizontal, vertical and two diagonals for each player.
 * - Since just have two players, we can use 1 for one player and -1 for other one.
 */
public class DesignTicTacToe {
    private int[] rowCounter;
    private int[] colCounter;
    private int diag1;
    private int diag2;
    private int n;
    public DesignTicTacToe(int n) {
        rowCounter = new int[n];
        colCounter = new int[n];
        this.n = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
    @param row The row of the board.
    @param col The column of the board.
    @param player The player, can be either 1 or 2.
            @return The current winning condition, can be either:
            0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        int add = player == 1 ? 1 : -1;
        rowCounter[row] += add;
        colCounter[col] += add;
        if(row == col) {
            diag1 += add;
        }
        if(row + col == n - 1) {
            diag2 += add;
        }
        if(Math.abs(rowCounter[row]) == n || Math.abs(colCounter[col]) == n || Math.abs(diag1) == n || Math.abs(diag2) == n) {
            return player;
        }
        return 0;
    }
}
