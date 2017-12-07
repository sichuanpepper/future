package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/description/
 *
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.

 The same repeated number may be chosen from C unlimited number of times.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [2, 3, 6, 7] and target 7,
 A solution set is:
 [
 [7],
 [2, 2, 3]
 ]
 * Created by someone on 12/3/17.
 */
public class Problem39 {

    /**
     * Analyze:
     * 1. Without duplicates.
     * 2. May be chosen from C unlimited.
     * 3. All positive integers.
     *
     * Integer Overflow?
     *
     * It's a typical DFS problem which can be solved by back tracking.
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length < 1 || target < 1) return res;
        Arrays.sort(candidates);
        helper(candidates, 0, target, new ArrayList<>(), 0, res);
        return res;
    }

    private void helper(int[] candidates, int start, int target, List<Integer> curElements, long curVal, List<List<Integer>> res) {
        if(curVal == target) {
            res.add(new ArrayList<>(curElements));
            return;
        } else if(curVal > target) {
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            curElements.add(candidates[i]);
            helper(candidates, i, target, curElements, curVal + candidates[i], res);
            curElements.remove(curElements.size() - 1);
        }
    }

    public static void main(String[] args) {
        Problem39 p = new Problem39();
        DisplayUtils.printLists(p.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
}
