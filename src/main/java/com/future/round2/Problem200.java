package com.future.round2;

/**
 * https://leetcode.com/problems/number-of-islands/description/
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 11110
 11010
 11000
 00000
 Answer: 1

 Example 2:

 11000
 11000
 00100
 00011
 Answer: 3
 * Created by someone on 11/14/17.
 */
public class Problem200 {
    /**
     * Analyze:
     * It's a typical union find problem, the first algorithm in my mind is union find.
     * There are three important methods for this algorithm, union, find and connected.
     * 1. There are maximum m * n islands, assign an unique id to each island.
     * 2. From the left up corner, join the right one and bottom one.
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length < 1) {
            return 0;
        }

        this.grid = grid;
        this.ids = new int[grid.length * grid[0].length];
        int index = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    ids[index++] = grid[i][j];
                    nums++;
                }
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i < grid.length - 1) {
                    union(i, j, i + 1, j);
                }
                if(j < grid[0].length - 1) {
                    union(i, j, i, j + 1);
                }
            }
        }

        return nums;
    }

    private char[][] grid;

    private int[] ids;

    private int nums;

    /**
     * Find the union id.
     *
     * @param i
     * @param j
     * @return
     */
    private int find(int i, int j) {
        int index = findIndex(i, j);
        //The parent id is stored.
        while (ids[index] != index) {
            index = ids[index];
        }
        return index;
    }

    private int findIndex(int i, int j) {
        return i * grid[0].length + j;
    }


    private void union(int i1, int j1, int i2, int j2) {
        int id1 = find(i1, j1);
        int id2 = find(i2, j2);
        if(id1 == id2) {
            return;
        }
        //union
        ids[id1] = id2;
        nums--;
    }

}
