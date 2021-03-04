package com.future.experience.fsbk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, 0, new ArrayList<>(), 0, target, res);
        return res;
    }

    private void helper(int[] candidates, int start, List<Integer> curPath,int curSum, int target, List<List<Integer>> res) {
        if(curSum == target) {
            res.add(new ArrayList<>(curPath));
            return;
        }
        if(start >= candidates.length || curSum > target) {
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            //recursive go deeper, for loop go wider.
            if(i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            curPath.add(candidates[i]);
            helper(candidates, i + 1, curPath, curSum + candidates[i], target, res);
            curPath.remove(curPath.size() - 1);
        }
    }
}
