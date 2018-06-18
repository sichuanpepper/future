package com.future.experience.linying;

/**
 * Created by xingfeiy on 6/17/18.
 */
public class PaintHouse {
    /**
     There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
     The cost of painting each house with a certain color is different. You have to paint all the houses such that
     no two adjacent houses have the same color.

     The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs0
     is the cost of painting house 0 with color red; costs1 is the cost of painting house 1 with color green, and so on...
     Find the minimum cost to paint all houses.

     Note: All costs are positive integers.
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        if(costs == null || costs.length < 1) return 0;
        int[] pre = new int[]{0, 0, 0};
        for(int[] cur : costs) {
            int[] tmp = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(i == j) continue;
                    tmp[i] = Math.min(pre[j] + cur[i], tmp[i]);
                }
            }
            pre = tmp;
        }
        return Math.min(Math.min(pre[0], pre[1]), pre[2]);
    }

    /**
     * There are a row of n houses, each house can be painted with one of the k colors.
     * The cost of painting each house with a certain color is different. You have to paint all the houses such that no
     * two adjacent houses have the same color.

     The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs0 is
     the cost of painting house 0 with color 0; costs1 is the cost of painting house 1 with color 2, and so on...
     Find the minimum cost to paint all houses.

     Note: All costs are positive integers.

     Follow up: Could you solve it in O(nk) runtime?
     * @param costs
     * @return
     */
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length < 1) return 0;
        //0: second min, 1: first min
        int[] twoMins = new int[]{0, 0};
        int preMinIndex = -1;
        for(int[] cur : costs) {
            int[] curMins = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
            int curIndex = 0;
            for(int i = 0; i < cur.length; i++) {
                int min = ((preMinIndex == i) ? twoMins[1] : twoMins[0]) + cur[i];
                //
                if(min < curMins[1]) {
                    curMins[0] = curMins[1];
                    curMins[min] = min;
                    curIndex = i;
                } else {
                    curMins[0] = min;
                }
            }
            preMinIndex = curIndex;
            twoMins = curMins;
        }
        return twoMins[1];
    }
}
