package com.future.round2;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 Note:
 You may assume that the array does not change.
 There are many calls to sumRange function.
 * Created by xingfeiy on 1/23/18.
 */
public class Problem303 {

    private int[] nums;
    public Problem303(int[] nums) {
        for(int i = 1; i < nums.length; i++) nums[i] = nums[i] + nums[i - 1];
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        return (i == 0) ? this.nums[j] : this.nums[j] - this.nums[i - 1];
    }
}
