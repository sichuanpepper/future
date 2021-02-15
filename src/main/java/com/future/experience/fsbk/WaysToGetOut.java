package com.future.experience.fsbk;

/**
 * Created by xingfeiy on 5/4/18.
 */
public class WaysToGetOut {
    /**
     * Given a matrix includes 0 and 1, 1 represents block and can't pass through, 0 represents free to go.
     * Find the minimum ways from left to right, example:
     * 1 0 0
     * 1 0 1
     * 0 0 1
     *
     * the minimum ways is 5.
     *
     * @param matrix
     * @return
     */
    public int minWays(int[][] matrix) {
        if(matrix == null || matrix.length < 1) return 0;
        for(int i = 0; i < matrix.length; i++) {
            //go through matrix[i][0]
            if(matrix[i][0] == 1) continue;
            helper(matrix, i, 1, 1);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    private int min = Integer.MAX_VALUE;

    /**
     * DFS
     * @param matrix
     * @param row
     * @param col
     * @param curSteps
     */
    private void helper(int[][] matrix, int row, int col, int curSteps) {
        if(row >= matrix.length || row < 0 || col < 0) return;
        //find a way
        if(col >= matrix[0].length) {
            min = Math.min(min, curSteps);
            return;
        }
        if(matrix[row][col] == 1) return;
        matrix[row][col] = 1;
        helper(matrix, row + 1, col, curSteps + 1);
        helper(matrix, row - 1, col, curSteps + 1);
        helper(matrix, row, col + 1, curSteps + 1);
        helper(matrix, row, col - 1, curSteps + 1);
        matrix[row][col] = 0;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][4];
        matrix[0] = new int[]{0, 0, 0, 1};
        matrix[1] = new int[]{1, 1, 0, 1};
        matrix[2] = new int[]{1, 0, 0, 1};
        matrix[3] = new int[]{1, 0, 1, 0};
        matrix[4] = new int[]{1, 0, 0, 0};

        WaysToGetOut ways = new WaysToGetOut();
        System.out.println(ways.minWays(matrix));
    }

}
