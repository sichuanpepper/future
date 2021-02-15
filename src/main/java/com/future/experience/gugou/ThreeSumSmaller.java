package com.future.experience.gugou;

import java.util.Arrays;

/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that
 * satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * Example:
 *
 * Input: nums = [-2,0,1,3], and target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 *              [-2,0,1]
 *              [-2,0,3]
 * Follow up: Could you solve it in O(n2) runtime?
 */
public class ThreeSumSmaller {
    /**
     * Sort it, go through element one by one(i) and check if there's any two elements(j, k) that satisfy condition j + k < target - i
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        if(nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        for(int i = 0; i < nums.length - 2; i++) {
            res += twoSumSmaller(nums, i + 1, nums.length - 1, target - nums[i]);
        }
        return res;
    }

    private int twoSumSmaller(int[] nums, int start, int end, int target) {
        int res = 0;
        while(end < nums.length && start < end) {
            if(nums[start] + nums[end] >= target) {
                end--;
            } else {
                res += (end - start);
                start++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSumSmaller p = new ThreeSumSmaller();
        System.out.println(p.threeSumSmaller(new int[]{-2,0,1,3}, 2));
        System.out.println(p.threeSumSmaller(new int[]{-2,0,1,3}, 0));
    }
}
