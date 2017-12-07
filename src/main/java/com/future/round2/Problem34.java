package com.future.round2;

/**
 * https://leetcode.com/problems/search-for-a-range/description/
 *
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 For example,
 Given [5, 7, 7, 8, 8, 10] and target value 8,

 return [3, 4].
 *
 * Created by someone on 12/3/17.
 */
public class Problem34 {
    /**
     * Analyze:
     * 1. Ascending order.
     * 2. May contain duplicates.
     * 3. The given target may not exist.
     * ===================================
     *  Sorted array + O(log n) runtime complexity, Binary Search?
     *
     *  Binary search can find the first target or last target in array easily, so the straightforward solution is do
     *  binary search twice.
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length < 1) return new int[]{-1, -1};
        int start = 0, end = nums.length - 1;
        int[] res = new int[]{-1, -1};
        //find the first target
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] >= target) {
                end = mid; //the real target could be in front of mid;
            } else {
                start = mid;
            }
        }
        if(nums[start] == target) {
            res[0] = start;
        } else if(nums[end] == target) {
            res[0] = end;
        } else {
            //the given target not found.
            return res;
        }

        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        //GOT ERROR: Use end first.
        res[1] = (nums[end] == target) ? end : start;
        return res;
    }

}
