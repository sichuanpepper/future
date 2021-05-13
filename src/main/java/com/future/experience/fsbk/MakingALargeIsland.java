package com.future.experience.fsbk;

import com.future.utils.DisplayUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/making-a-large-island/
 *
 */
public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        int max = 0;
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid.length; col++) {
                if(grid[row][col] == 0) {
                    grid[row][col] = 1;
                    int res = findOne(grid, row, col, new boolean[grid.length][grid[0].length]);
                    max = Math.max(max, res);
                    grid[row][col] = 0;
                }
            }
        }
        return max == 0 ? grid.length * grid[0].length : max;
    }

    private int findOne(int[][] grid, int row, int col, boolean[][] visited) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return 0;
        }
        if(grid[row][col] != 1 || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        return findOne(grid, row - 1, col, visited) + findOne(grid, row + 1, col, visited)
                + findOne(grid, row, col - 1, visited) + findOne(grid, row, col + 1, visited) + 1;
    }

    private int cntOne(int[][] grid, int row, int col, int groupId) {
        if(row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1) {
            return 0;
        }

        if(grid[row][col] != 1) {
            return 0;
        }

        grid[row][col] = groupId;

        int left = cntOne(grid, row, col - 1, groupId);
        int right = cntOne(grid, row, col + 1,  groupId);
        int up = cntOne(grid, row - 1, col, groupId);
        int bottom = cntOne(grid, row + 1, col, groupId);
        return left + right + up + bottom + 1;
    }

    public int largestIslandV2(int[][] grid) {
        int row = grid.length, col = grid[0].length, groupId = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    int cnt = cntOne(grid, i, j, groupId);
                    map.put(groupId--, cnt);
                }
            }
        }
        int max = -1;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 0) {
                    Set<Integer> groups = new HashSet<>();
                    if(i > 0 && grid[i - 1][j] < 0) groups.add(grid[i - 1][j]);
                    if(j > 0 && grid[i][j - 1] < 0) groups.add(grid[i][j - 1]);
                    if(i < row - 1 && grid[i + 1][j] < 0) groups.add(grid[i + 1][j]);
                    if(j < col - 1 && grid[i][j + 1] < 0) groups.add(grid[i][j + 1]);
                    int res = 0;
                    for(int group : groups) {
                        res += map.get(group);
                    }
                    max = Math.max(max, res + 1);
                }
            }
        }
        return max == -1 ? row * col : max;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{
//                new int[]{1, 0, 1},
//                new int[]{1, 0, 0},
//                new int[]{0, 1, 1}
//        };
        int[][] matrix = new int[][]{
                new int[]{1, 0},
                new int[]{0, 1}
        };
        MakingALargeIsland p = new MakingALargeIsland();
        System.out.println(p.largestIslandV2(matrix));
        DisplayUtils.printTwoDimensionsArray(matrix);
    }
}
