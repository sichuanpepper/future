package com.future.experience.fsbk;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
 */
public class NumberofSubsequencesThatSatisfytheGivenSumCondition {
    public int numSubseq(int[] nums, int target) {
        int res = 0, M = (int)1e9 + 7;

        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int val = nums[left] + nums[right];
            if(val > target) {
                right--;
            } else {
                res = (res + (1 << (right - left)) % M) % M;
                left++;
            }
        }
        return res;
    }
}
