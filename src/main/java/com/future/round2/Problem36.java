package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-sudoku/description/
 *
 * Created by someone on 12/3/17.
 */
public class Problem36 {
    /**
     * Analyze:
     * Same row
     * Same column
     * Small units
     *  - left upper row % 3 == 0 && column % 3 == 0
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9) return false;
        Map<Character, List<List<Integer>>> map = new HashMap<>();
        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if(board[row][col] == '.') continue;
                if(map.containsKey(board[row][col])) {
                    for(List<Integer> pos : map.get(board[row][col])) {
                        //check row and col
                        if(pos.get(0) == row || pos.get(1) == col) return false;
                        //check diagonal
//                        if(pos.get(0) == pos.get(1) && col == row) return false;
//                        if(pos.get(0) + pos.get(1) == 8 && col + row == 8) return false;
                        //check small units
                        if(getCell(row, col) == getCell(pos.get(0), pos.get(1))) return false;
                    }
                }
                List<Integer> curPos = new ArrayList<>();
                curPos.add(row);
                curPos.add(col);
                List<List<Integer>> res = map.getOrDefault(board[row][col], new ArrayList<>());
                res.add(curPos);
                map.put(board[row][col], res);
            }
        }
        return true;
    }

    private int getCell(int row, int col) {
        return row / 3 * 3 + col / 3;
    }

    public static void main(String[] args) {
        char[][] board = new char[9][9];
//        board[0] = new char[]{'.','.','5','.','.','.','.','.','6'};
//        board[1] = new char[]{'.','.','.','.','1','4','.','.','.'};
//        board[2] = new char[]{'.','.','.','.','.','.','.','.','.'};
//        board[3] = new char[]{'.','.','.','.','.','9','2','.','.'};
//        board[4] = new char[]{'5','.','.','.','.','2','.','.','.'};
//        board[5] = new char[]{'.','.','.','.','.','.','.','3','.'};
//        board[6] = new char[]{'.','.','.','5','4','.','.','.','.'};
//        board[7] = new char[]{'3','.','.','.','.','.','4','2','.'};
//        board[8] = new char[]{'.','.','.','2','7','.','6','.','.'};


        board[0] = new char[]{'.','.','.','.','.','.','.','.','2'};
        board[1] = new char[]{'.','.','.','.','.','.','6','.','.'};
        board[2] = new char[]{'.','.','1','4','.','.','8','.','.'};
        board[3] = new char[]{'.','.','.','.','.','.','.','.','.'};
        board[4] = new char[]{'.','.','.','.','.','.','.','.','.'};
        board[5] = new char[]{'.','.','.','.','3','.','.','.','.'};
        board[6] = new char[]{'5','.','8','6','.','.','.','.','.'};
        board[7] = new char[]{'.','9','.','.','.','.','4','.','.'};
        board[8] = new char[]{'.','.','.','.','5','.','.','.','.'};
        Problem36 p = new Problem36();
        System.out.println(p.isValidSudoku(board));
    }
}
