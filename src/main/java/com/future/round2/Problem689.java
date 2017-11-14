package com.future.round2;

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
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];

        // all elements are init as 0 by defalut.
        int[][] top3 = new int[3][2];
        //init the start index as -1
        for(int[] row : top3) {
            row[1] = -1;
        }

        for(int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
            if(i < k) {
                continue;
            }
            int tmp = sum[i + 1] - sum[i + 1 - k];
            //

        }

        return new int[]{top3[0][1], top3[1][1], top3[2][1]};
    }
}
