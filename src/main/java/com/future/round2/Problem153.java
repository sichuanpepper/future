package com.future.round2;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Find the minimum element.

 You may assume no duplicate exists in the array.

 * Created by xingfeiy on 1/25/18.
 */
public class Problem153 {
    public int findMin(int[] nums) {
        if(nums == null || nums.length < 1) return Integer.MIN_VALUE;
        if(nums.length == 1 || nums[0] < nums[nums.length - 1]) return nums[0];
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] > nums[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return Math.min(nums[start], nums[end]);
    }
}
