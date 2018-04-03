package com.future.round2;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 *
 Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one
 LIS combination, it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.

 Follow up: Could you improve it to O(n log n) time complexity?
 * Created by xingfeiy on 4/1/18.
 */
public class Problem300 {

    /**
     * Analyze:
     * It's a typical DP problem,
     * - Overlapping subproblems
     * - optimal substructures.
     *
     * Time complexity: O(n * n)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        int[] dp = new int[nums.length];
        //init
        Arrays.fill(dp, 1);
        int max = 1;
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    /**
     * Analyze:
     * The follow up question asked solve this issue in O(nlog(n))
     * It seems that do binary search for each elements since binary search runs in log(n).
     * But the given array is unsorted.
     * @param nums
     * @return
     */
    public int followup(int[] nums) {
        return 0;
    }
}
