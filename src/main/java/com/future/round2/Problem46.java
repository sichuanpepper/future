package com.future.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/description/
 * Given a collection of distinct numbers, return all possible permutations.

 For example,
 [1,2,3] have the following permutations:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]
 * Created by someone on 12/3/17.
 */
public class Problem46 {
    /**
     * Analyze:
     * It's a typical back tracking problem.
     * Notices, there's no duplicates here, otherwise the problem could be a little bit difficult.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 1) return res;
        helper(nums, new ArrayList<>(), res);
        return res;
    }

    private void helper(int[] nums, List<Integer> curVal, List<List<Integer>> res) {
        if(curVal.size() >= nums.length) {
            res.add(new ArrayList<>(curVal));
            return;
        }

        for(int num : nums) {
            if(curVal.contains(num)) continue;
            curVal.add(num);
            helper(nums, curVal, res);
            curVal.remove(curVal.size() - 1);
        }
    }
}
