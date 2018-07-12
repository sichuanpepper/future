package com.future.experience.aibiying;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given: An array of strings where L indicates land and W indicates water,
 * and a coordinate marking a starting point in the middle of the ocean.
 *
 * Challenge: Find and mark the ocean in the map by changing appropriate Ws to Os.
 * An ocean coordinate is defined to be the initial coordinate if a W, and
 * any coordinate directly adjacent to any other ocean coordinate.
 *
 * void findOcean(String[] map, int row, int column);
 *
 * String[] map = new String[]{
 * "WWWLLLW",
 * "WWLLLWW",
 * "WLLLLWW"
 * };
 * printMap(map);
 *
 * STDOUT:
 * WWWLLLW
 * WWLLLWW
 * WLLLLWW
 *
 * findOcean(map, 0, 1);
 *
 * printMap(map);
 *
 * STDOUT:
 * OOOLLLW
 * OOLLLWW
 * OLLLLWW
 *
 *
 *
 * Created by xingfeiy on 6/13/18.
 */
public class FindingOcean {
    public void floodFill(char[][] board, int i, int j, char oldColor, char
            newColor) {
        if (board[i][j] != oldColor || board[i][j] == newColor) {
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i * board[0].length + j);
        board[i][j] = newColor;
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            int m = pos / board[0].length;
            int n = pos % board[0].length;
            if (m + 1 < board.length && board[m + 1][n] == oldColor) {
                queue.add((m + 1) * board[0].length + n);
                board[m + 1][n] = newColor;
            }
            if (m - 1 >= 0 && board[m - 1][n] == oldColor) {
                queue.add((m - 1) * board[0].length + n);
                board[m - 1][n] = newColor;
            }
            if (n + 1 < board[0].length && board[m][n + 1] == oldColor) {
                queue.add(m * board[0].length + n + 1);
                board[m][n + 1] = newColor;
            }
            if (n - 1 >= 0 && board[m][n - 1] == oldColor) {
                queue.add(m * board[0].length + n - 1);
                board[m][n - 1] = newColor;
            }
        }
    }
}
