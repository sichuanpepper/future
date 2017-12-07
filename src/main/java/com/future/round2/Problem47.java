package com.future.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations-ii/description/
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

 For example,
 [1,1,2] have the following unique permutations:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]

 * Created by xingfeiy on 12/6/17.
 */
public class Problem47 {
    /**
     * Analyze:
     * It's a similar issue as Problem 46, the difference here is the given array might contain duplicates.
     * So, for the array problem, think about the duplicate case first.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 1) return res;
        Arrays.sort(nums);
        helper(nums, new ArrayList<>(), new boolean[nums.length], res);
        return res;
    }

    private void helper(int[] nums, List<Integer> cur, boolean[] visited, List<List<Integer>> res) {
        if(cur.size() >= nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            if(i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            visited[i] = true;
            cur.add(nums[i]);
            helper(nums, cur, visited, res);
            visited[i] = false;
            cur.remove(cur.size() - 1);
        }
    }
}
