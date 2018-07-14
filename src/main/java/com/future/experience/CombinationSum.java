package com.future.experience;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xingfeiy on 7/10/18.
 */
public class CombinationSum {
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
}
