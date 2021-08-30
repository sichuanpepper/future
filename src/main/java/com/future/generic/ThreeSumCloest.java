package com.future.generic;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-closest/
 *
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumCloest {
    /**
     * Thoughts:
     * The brute force solution is just try all combinations, use 3 for loop, the time complexity is O(n^3).
     *
     * A better solution is sort array first, and do the binary search for the third for loop, then the TC became to O(n^2 * log(n))
     *
     * The third solution is, sort array first, and then we traversal numbers from right to left, for each number, try to narrow down the window on its left.
     * As we know, for a sorted array, move left make the (left + right) bigger, move right makes (left + right) smaller.
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE, res = 0;
        for(int i = nums.length - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                int curSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(curSum - target) < diff) {
                    diff = Math.abs(curSum - target);
                    res = curSum;
                }
                if (curSum == target) {
                    return curSum;
                } else if (curSum < target) {
                    left++;
                } else {
                    right--;
                }

            }
        }
        return res;
    }
}
