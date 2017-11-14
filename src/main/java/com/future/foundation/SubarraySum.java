package com.future.foundation;

import java.util.List;

/**
 * There are some common problems about the sub array sum.
 * 1. Find all sub arrays which sum up to given value.
 * 2. Find largest sub array which sum up to given value.
 * 3, Find the sub array which has largest sum.
 *
 * Created by someone on 10/31/17.
 */
public class SubarraySum {

//    public static List<List<Integer>> findAllSubarray(int[] nums, int target) {}

//    public static List<Integer> findLargestSubarray(int[] nums, int target) {}

    /**
     * Questions: Find the subarray which has maximum sum.
     * Test case: [-2, -1, -3] => -1
     * Test case: [1, 2, 3] => 6
     * Test case: [0, -3, 6, -2, 5] => 9
     * Analyze:
     * 1. Negative number makes the sum smaller, so if current sum is negative, it's useless for back forward subarray.
     * @param nums
     * @return
     */
    public static int findLargestSum(int[] nums) {
        if(nums == null || nums.length < 1) {
            return Integer.MIN_VALUE;
        }

        int sumHere = nums[0];
        int curMaxSum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            sumHere += nums[i];
            curMaxSum = Math.max(sumHere, curMaxSum);
            sumHere = Math.max(0, sumHere);
        }
        return curMaxSum;
    }
}
