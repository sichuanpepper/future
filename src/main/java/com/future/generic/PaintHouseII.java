package com.future.generic;

/**
 * https://www.lintcode.com/problem/516/
 *
 * Description
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example,
 * costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2,
 * and so on... Find the minimum cost to paint all houses.
 *
 * Thoughts:
 * We can go through houses one by one, for each house, we care about the minimum cost in the previous house, but it's not allowed two
 * adjacent houses have same color, so we have to care about the two minimum costs, so we always can get the minimum cost of previous.
 */
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length < 1 || costs[0].length < 1) {
            return 0;
        }
        // write your code here
        int n = costs.length, k = costs[0].length;

        int[][] minTwo = new int[2][2];
        minTwo[0][0] = -1;
        minTwo[1][0] = -1;

        for(int i = 0; i < n; i++) {
            int[][] tmp = new int[2][2];
            tmp[0][0] = -1;
            tmp[1][0] = -1;
            for(int j = 0; j < k; j++) {
                if(j == minTwo[0][0]) {
                    updateMin(tmp, j, minTwo[1][1] + costs[i][j]);
                } else if(j == minTwo[1][0]) {
                    updateMin(tmp, j, minTwo[0][1] + costs[i][j]);
                } else {
                    updateMin(tmp, j, minTwo[0][1] + costs[i][j]);
                }
            }
            minTwo = tmp;
        }
        return minTwo[0][1];
    }

    private void updateMin(int[][] minTwo, int k, int val) {
        if(minTwo[0][0] == -1) {
            minTwo[0][0] = k;
            minTwo[0][1] = val;
            return;
        }

        if(val < minTwo[0][1]) { // move minOne to minTwo
            minTwo[1][0] = minTwo[0][0];
            minTwo[1][1] = minTwo[0][1];
            minTwo[0][0] = k;
            minTwo[0][1] = val;
        } else if(minTwo[1][0] == -1 || val < minTwo[1][1]) {
            minTwo[1][0] = k;
            minTwo[1][1] = val;
        }
    }
}
