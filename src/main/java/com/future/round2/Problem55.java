package com.future.round2;

/**
 * https://leetcode.com/problems/jump-game/description/
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 For example:
 A = [2,3,1,1,4], return true.

 A = [3,2,1,0,4], return false.

 * Created by xingfeiy on 12/8/17.
 */
public class Problem55 {
    /**
     * Analyze:
     * From the first position, we can get the a range that we can reached from this position, then,
     * we can found the next maximum range we can reached, do it iterated util the last position is reached.
     * OR
     * Just go through position one by one, and track the farthest position we can reach.
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length < 1) return false;
        int reached = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(reached < i) return false;
            reached = Math.max(reached, nums[i] + i);
            if(reached >= nums.length - 1) return true;
        }
        return true;
    }
}
