package com.future.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/description/
 * -- facebook
 * Created by xingfeiy on 11/21/17.
 */
public class Problem78 {
    /**
     * Analyze:
     * It's typical back tracking problem, keep the result from each step into the result.
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null) return res;
        helper(nums, 0, res, new ArrayList<Integer>());
        return res;
    }

    private void helper(int[] nums, int start, List<List<Integer>> res, ArrayList<Integer> curRes) {
        res.add(new ArrayList<>(curRes));
        if(start >= nums.length) return;
        for(int i = start; i < nums.length; i++) {
            curRes.add(nums[i]);
            helper(nums, i + 1, res, curRes);
            curRes.remove(curRes.size() - 1);
        }
    }
}
