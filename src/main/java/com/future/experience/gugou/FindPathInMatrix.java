package com.future.experience.gugou;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定一个 矩阵， 寻找是否存在路径可以从左上到右下。 每一步只能在上下左右四个方向走 而且 下一步的值要小于等于当前值。（DFS/BFS)
 * Follow up: 1. 求所有路径中 经过的grid 和最小的路径 2）如果允许花钱增加格子的值 （只增不减 比方说 grid[0][1] 从9 增加到11 那么cost 就是2） 问cost 最小的路径cost是多少
 */
public class FindPathInMatrix {
    public boolean existWay(int[][] matrix) {
        return helper(matrix, 0, 0, new boolean[matrix.length][matrix[0].length]);
    }

    private boolean helper(int[][] matrix, int row, int col, boolean[][] visited) {
        if(row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return false;
        }

        if(visited[row][col]) {
            return false;
        }

        if(row == matrix.length - 1 && col == matrix[0].length - 1) {
            return true;
        }
        int val = matrix[row][col];
        visited[row][col] = true;
        if(row > 0 && matrix[row - 1][col] < val) {
            if(helper(matrix, row - 1, col, visited)) {
                return true;
            }
        }

        if(row < matrix.length - 1 && matrix[row + 1][col] < val) {
            if(helper(matrix, row + 1, col, visited)) {
                return true;
            }
        }

        if(col > 0 && matrix[row][col - 1] < val) {
            if(helper(matrix, row, col - 1, visited)) {
                return true;
            }
        }

        if(col < matrix[0].length - 1 && matrix[row][col + 1] < val) {
            if(helper(matrix, row, col + 1, visited)) {
                return true;
            }
        }
        return false;
    }


    /**
     * The problem is find the shortest path in a graph
     * @param matrix
     * @return
     */
    public int findMinPath(int[][] matrix) {
        //int[0]: row, int[1]: col, int[2]: sum of path
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        queue.offer(new int[]{0, 0, matrix[0][0]});
        while (!queue.isEmpty()) {
            int[] pairs = queue.poll();
            int row = pairs[0], col = pairs[1];
            if(row == matrix.length - 1 && col == matrix[0].length - 1) {
                return pairs[2];
            }
            //check neighbors
            if(row > 0 && matrix[row - 1][col] < matrix[row][col]) {
                queue.offer(new int[]{row - 1, col, pairs[2] + matrix[row - 1][col]});
            }
            if(row < matrix.length - 1 && matrix[row + 1][col] < matrix[row][col]) {
                queue.offer(new int[]{row + 1, col, pairs[2] + matrix[row + 1][col]});
            }
            if(col > 0 && matrix[row][col - 1] < matrix[row][col]) {
                queue.offer(new int[]{row, col - 1, pairs[2] + matrix[row][col - 1]});
            }
            if(col < matrix[0].length - 1 && matrix[row][col + 1] < matrix[row][col]) {
                queue.offer(new int[]{row, col + 1, pairs[2] + matrix[row][col + 1]});
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * 如果允许花钱增加格子的值 （只增不减 比方说 grid[0][1] 从9 增加到11 那么cost 就是2） 问cost 最小的路径cost是多少
     * @param matrix
     * @return
     */
    public int findMinCost(int[][] matrix) {
        //int[] is an array of length 4, int[0]: row, int[1]: col, int[2]: cur min sum, int[3]: cost
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[3], b[3]));
        queue.offer(new int[]{0, 0, matrix[0][0], 0});
        while (!queue.isEmpty()) {
            int[] pairs = queue.poll();
            int row = pairs[0], col = pairs[1];
            if(row == matrix.length - 1 && col == matrix[0].length - 1) {
                return pairs[3];
            }
            if(row > 0) {
                int cost = (matrix[row - 1][col] < matrix[row][col]) ? 0 : matrix[row - 1][col] - matrix[row][col];
                if(matrix[row - 1][col] == matrix[row][col]) {
                    cost = 1;
                }
                queue.offer(new int[]{row - 1, col, pairs[2] + matrix[row - 1][col], pairs[3] + cost});
            }
            if(row < matrix.length - 1) {
                int cost = (matrix[row + 1][col] < matrix[row][col]) ? 0 : matrix[row + 1][col] - matrix[row][col];
                if(matrix[row + 1][col] == matrix[row][col]) {
                    cost = 1;
                }
                queue.offer(new int[]{row + 1, col, pairs[2] + matrix[row + 1][col], pairs[3] + cost});
            }
            if(col > 0) {
                int cost = (matrix[row][col - 1] < matrix[row][col]) ? 0 : matrix[row][col - 1] - matrix[row][col];
                if(matrix[row][col - 1] == matrix[row][col]) {
                    cost = 1;
                }
                queue.offer(new int[]{row, col - 1, pairs[2] + matrix[row][col - 1], pairs[3] + cost});
            }
            if(col < matrix[0].length - 1) {
                int cost = (matrix[row][col + 1] < matrix[row][col]) ? 0 : matrix[row][col + 1] - matrix[row][col];
                if(matrix[row][col + 1] == matrix[row][col]) {
                    cost = 1;
                }
                queue.offer(new int[]{row, col + 1, pairs[2] + matrix[row][col + 1], pairs[3] + cost});
            }

        }
        return Integer.MAX_VALUE;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                new int[] {9, 19, 8, 9},
                new int[] {19, 5, 7, 8},
                new int[] {7, 4, 3, 2},
                new int[] {6, 15, 10, 0}
        };
        FindPathInMatrix p = new FindPathInMatrix();
        System.out.println(p.existWay(matrix));

        System.out.println(p.findMinPath(matrix));
        System.out.println(p.findMinCost(matrix));

    }

}
