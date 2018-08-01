package com.future.round2;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 *
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

 Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

 Return the result as a list of indices representing the starting position of each interval (0-indexed).
 If there are multiple answers, return the lexicographically smallest one.

 Example:
 Input: [1,2,1,2,6,7,5,1], 2
 Output: [0, 3, 5]
 Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 * Created by someone on 11/5/17.
 */
public class Problem689 {
    /**
     * Analyze:
     * sum[m] - sum[n] = nums[n+1] + nums[n+2] +...+ nums[m]
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] res = new int[3];
        if(k < 1 || nums.length * 3 < k) return res;
        List<Integer> sum = new LinkedList<>();
        return res;
    }
}
