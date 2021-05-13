package com.future.experience.linying.eley;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given the grid of integers, each number representing one type of enemy.
 * If you attack one enemy, such as enemy type 1 at (0,0) you can kill all the connected enemies reachable from (0.0),
 * so in the following example five enemies from row 0 and column 0 will die. You want to wipe out all enemies with the
 * minimal number of attacks, and attacking the most possible enemies at once each time. Return the best attack order.
 *
 *
 * grid = [[1, 1, 1],
 *         [1, 0, 0],
 *         [1, 0, 1]]
 * Given the grid of players, if you attack enemy type 1, the best order is (0,0) or any of the five positions on first
 * row or first column, which will kill five, then (2,2) which will kill one.
 *
 * Thoughts:
 * - Go through all elements in the grid one by one, if the element is 1, start from this position and do the DFS traversal,
 *   and return its count, and then return the value sorted by count, for the element 1 that has been visited, we can just flip it to 0.
 */
public class BestAttackStrategy {
    public List<int[]> findBestStrategy(int[][] grid) {
        List<int[]> res = new ArrayList<>();
        if(grid == null || grid.length < 1) {
            return res;
        }

        int row = grid.length, col = grid[0].length;
        for(int i  = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    int cnt = countOne(grid, i, j);
                    res.add(new int[]{i, j, cnt});
                }
            }
        }
        Collections.sort(res, (a, b) -> Integer.compare(b[2], a[2]));
        return res;
    }

    private int countOne(int[][] grid, int row, int col) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 1) {
            return 0;
        }
        grid[row][col] = 0;

        return countOne(grid, row - 1, col) + countOne(grid, row + 1, col) +
                countOne(grid, row, col - 1) + countOne(grid, row, col + 1) + 1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                new int[] {1, 0, 1},
                new int[] {1, 0, 0},
                new int[] {1, 0, 1}
        };
        List<int[]> res = new BestAttackStrategy().findBestStrategy(grid);
        for(int[] r : res) {
            DisplayUtils.printArray(r);
        }
    }
}
