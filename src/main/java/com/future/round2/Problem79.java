package com.future.round2;

/**
 * https://leetcode.com/problems/word-search/description/

 Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or
 vertically neighboring. The same letter cell may not be used more than once.

 For example,
 Given board =

 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]
 word = "ABCCED", -> returns true,
 word = "SEE", -> returns true,
 word = "ABCB", -> returns false.
 * Created by xingfeiy on 12/17/17.
 */
public class Problem79 {
    /**
     * Analyze:
     * DFS or Back tracking?
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length < 1 || word == null || word.length() < 1
                || board.length * board[0].length < word.length()) return false;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(helper(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean helper(char[][] board, String word, int curRow, int curCol, int index) {
        if(index >= word.length()) return true;
        if(curRow < 0 || curRow >= board.length || curCol < 0 || curCol >= board[0].length) return false;
        if(board[curRow][curCol] != word.charAt(index)) return false;
        board[curRow][curCol] = ' ';
        if(helper(board, word, curRow - 1, curCol, index + 1) || helper(board, word, curRow + 1, curCol, index + 1)
                || helper(board, word, curRow, curCol - 1, index + 1) || helper(board, word, curRow, curCol + 1, index + 1)) {
            return true;
        }
        board[curRow][curCol] = word.charAt(index);
        return false;

    }

}
