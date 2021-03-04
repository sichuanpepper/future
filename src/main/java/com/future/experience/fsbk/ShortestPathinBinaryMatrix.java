package com.future.experience.fsbk;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 *
 * Takeaways:
 * - To find shortest path in a graph, it's better to use BFS.
 */
public class ShortestPathinBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        if(grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1;
        }
        queue.offer(new int[]{0, 0});
        int level = 0;
        while(!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if(cur[0] == (grid.length - 1) && cur[1] == (grid[0].length - 1)) {
                    return level;
                }

                grid[cur[0]][cur[1]] = 1;
                for(int r = -1; r <= 1; r++) {
                    int[] array = r == 0 ? new int[]{-1, 1} : new int[]{-1, 0, 1};
                    for(int j : array) {
                        addToQueue(cur[0] + r, cur[1] + j, queue, grid);
                    }
                }
            }
        }
        return -1;
    }

    private void addToQueue(int row, int col, Queue<int[]> queue, int[][] grid) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 0) {
            return;
        }
        grid[row][col] = 1;
        queue.offer(new int[]{row, col});
    }
}
