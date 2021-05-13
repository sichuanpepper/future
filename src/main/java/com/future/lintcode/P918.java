package com.future.lintcode;

import java.util.Arrays;

/**
 * https://www.lintcode.com/problem/3sum-smaller/
 *
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * Example
 * Example1
 *
 * Input:  nums = [-2,0,1,3], target = 2
 * Output: 2
 * Explanation:
 * Because there are two triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 * Example2
 *
 * Input: nums = [-2,0,-1,3], target = 2
 * Output: 3
 * Explanation:
 * Because there are three triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 * [-2, -1, 3]
 * Challenge
 * Could you solve it in O(n2) runtime?
 */
public class P918 {
    /**
     * @param nums:  an array of n integers
     * @param target: a target
     * @return: the number of index triplets satisfy the condition nums[i] + nums[j] + nums[k] < target

    - arrays contains any integer.
    - unsorted.
    - may has duplicated num.

    - Sort array
    - Go through ele one by one, for each ele at postion p1:
    - Find two elements(p2, p3) in left sub array(p1 + 1, end), p2 start from p1 + 1, and p3 start from end.
    - sum(p1, p2, p3)
    - sum >= target, need to find a smaller sum, p3--
    - sum < target, find one res, and all ele between p2 and p3 are qualified.
     */
    public int threeSumSmaller(int[] nums, int target) {
        // Write your code here
        if(nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        for(int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum >= target) {
                    right--;
                } else {
                    res += (right - left);
                    left++;
                }
            }
        }
        return res;
    }
}
