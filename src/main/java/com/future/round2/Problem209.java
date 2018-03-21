package com.future.round2;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/
 *
 *
 Given an array of n positive integers and a positive integer s,
 find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.

 * Created by xingfeiy on 3/8/18.
 */
public class Problem209 {
    /**
     * The problem is going to find a contiguous subarray, you can image the subarray as a window, you can shift left
     * side or right side, if the sum of the elements in window which is small than s, then shift right side, otherwise,
     * shift left side.
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        int left = 0, right = 0, sum = 0, min = Integer.MAX_VALUE;
        while(right < nums.length) {
            sum += nums[right++];
            while(sum >= s) {
                min = Math.min(min, right - left);
                sum -= nums[left++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
