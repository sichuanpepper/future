package com.future.foundation.debug;

/**
 * Created by xingfeiy on 8/20/17.
 */
public class Debug200 {
    private int count;
    private int[] index;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }

        //init
        count = 0;
        index = new int[grid.length * grid[0].length];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    index[i * grid[0].length + j] = i * grid[0].length + j;
                } else {
                    index[i * grid[0].length + j] = -1;
                }
            }
        }

        //calculate connective
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '0') {
                    continue;
                }
                if (i > 0 && grid[i - 1][j] == '1') {
                    union(i * grid[0].length + j, (i - 1) * grid[0].length + j);
                }

                if(j > 0 && grid[i][j - 1] == '1') {
                    union(i * grid[0].length + j, i * grid[0].length + j - 1);
                }

                if(i < grid.length - 1 && grid[i + 1][j] == '1') {
                    union(i * grid[0].length + j, (i + 1) * grid[0].length + j);
                }

                if(j < grid[0].length - 1 && grid[i][j + 1] == '1') {
                    union(i * grid[0].length + j, i * grid[0].length + j + 1);
                }
            }
        }
        return count;
    }

    //union
    private void union(int a, int b) {
        int indexA = find(a);
        int indexB = find(b);
        if(indexA == indexB) {
            return;
        }

        index[indexB] = indexA;
        count--;
    }

    private int find(int a) {
        while(index[a] != a) {
            a = index[a];
        }
        return a;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{"111".toCharArray(), "010".toCharArray(), "111".toCharArray()};
        System.out.println(new Debug200().numIslands(grid));
    }
}
