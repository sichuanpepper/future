package com.future.round2;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 *
 Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.


 * Created by xingfeiy on 12/8/17.
 */
public class Problem53 {
    /**
     * Analyze:
     *          -2, 1, -3, 4, -1, 2, 1, -5, 4
     * sumHere  0, 1,  0,  4,  3, 5, 6, 1,  5  (sumHere = pre_sumHere + cur_num > 0 ? pre_sumHere + cur_num : 0)
     * maxSum
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length < 1) return Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE, sumHere = 0;
        for(int i = 0; i < nums.length; i++) {
            maxSum = sumHere > 0 ? Math.max(sumHere + nums[i], maxSum) : Math.max(maxSum, nums[i]);
            sumHere = sumHere + nums[i] > 0 ? sumHere + nums[i] : 0;
        }
        return maxSum;
    }

    public int maxSubArray2(int[] nums) {
        if(nums == null || nums.length < 1) return Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE, sumHere = 0;
        for(int i = 0; i < nums.length; i++) {
            maxSum = sumHere > 0 ? Math.max(sumHere + nums[i], maxSum) : Math.max(maxSum, nums[i]);
            sumHere = sumHere + nums[i] > 0 ? sumHere + nums[i] : 0;
        }
        return maxSum;
    }

    public int maxSubArray3(int[] nums) {
        if(nums == null || nums.length < 1) return Integer.MIN_VALUE;

        int maxSoFar = nums[0], maxHere = nums[0];
        for(int i = 1; i < nums.length; i++) {
            maxSoFar = Math.max(maxSoFar, maxHere + nums[i]);
            maxHere = Math.max(0, maxHere + nums[i]);
        }
        return maxSoFar;
    }
}
