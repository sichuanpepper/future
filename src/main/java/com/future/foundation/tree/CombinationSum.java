package com.future.foundation.tree;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate
 * numbers sums to T. The same repeated number may be chosen from C unlimited number of times.

 Note: All numbers (including target) will be positive integers. Elements in a combination (a1, a2, ... , ak) must be
 in non-descending order. (ie, a1 <= a2 <= ... <= ak). The solution set must not contain duplicate combinations. For example,
 given candidate set 2,3,6,7 and target 7, A solution set is:

 [7]
 [2, 2, 3]
 * Created by someone on 6/17/17.
 */
public class CombinationSum {
    /**
     * Analyze:
     * The first impression of this kind of problem should be DFS, think the example like this:
     *                                       2
     *              /              /              \             \
     *           (2, 3, 6, 7)  (2, 3, 6, 7)  (2, 3, 6, 7)  (2, 3, 6, 7)
     *           / ...
     *      (2, 3, 6, 7)....
     *  To solve DFS problem, we often use recursion.
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length < 1) {
            return res;
        }

        combination(candidates, target, res, new ArrayList<>());
        return res;
    }


    private static void combination(int[] candidates, int target, List<List<Integer>> results, List<Integer> cur) {
        if(target == 0) {
            List<Integer> tmp = new ArrayList<>(cur);
            results.add(tmp);
            return;
        }

        for(int c : candidates) {
            if(c > target) {
                continue;
            }

            cur.add(c);
            combination(candidates, target - c, results, cur);
            cur.remove(cur.size() - 1);
        }
    }


    public static int dp(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[][] res = new int[row][col];

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(i == 0 && j == 0) {
                    res[i][j] = grid[0][0];
                } else if(i == 0) {
                    res[i][j] = grid[i][j] + grid[i][j - 1];
                } else if(j == 0) {
                    res[i][j] = grid[i][j] + grid[i - 1][j];
                } else {
                    res[i][j] = grid[i][j] + Math.min(res[i - 1][j], res[i][j - 1]);
                }
            }
        }
        DisplayUtils.printTwoDimensionsArray(res);
        return res[row - 1][col -1 ];
    }


    public static void main(String[] args) {
        int[][] array = new int[2][2];
        array[0][0] = 1;
        array[0][1] = 2;
        array[1][0] = 1;
        array[1][1] = 1;
        System.out.println(dp(array));
    }
}
