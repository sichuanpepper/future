package com.future.round2;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 *
 Follow up for "Search in Rotated Sorted Array":
 What if duplicates are allowed?

 Would this affect the run-time complexity? How and why?
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Write a function to determine if a given target is in the array.

 The array may contain duplicates.

 * Created by xingfeiy on 12/17/17.
 */
public class Problem81 {
    /**
     * Analyze:
     * It's the follow up question of problem 33.
     * - Sorted Array.
     * - May contain duplicates.
     * - The target may exist or may not.
     * - Given nums could be null or empty.
     *
     * Compare with problem 33 which doesn't contain duplicates, the duplicates always make trouble.
     * For example: 4 5 6 7 8 1 2 3 4, and we are looking for 5.
     * If nums[mid] > nums[end] (mid point located in first sorted array)   (COULD BE WRONG if there are duplicates)
     *  - if nums[mid] > target
     *      -- if nums[start] <= target, end <- mid - 1
     *      -- else start <- mid + 1
     *  - start <- mid + 1
     * else (mid point located in second array.)
     *  - if nums[mid] < target
     *      -- if nums[end] >= target, start <- mid + 1
     *      -- else end <- mid - 1
     *  - else end <- mid - 1
     *
     * As the analysis above, if there are duplicates, we may not locate where's the mid point, example
     * 2, 2, 3, 2, we can find the mid point is 2 which located index 1, it equals end, but it actually located in first sorted array.
     * In this case, we have to do search both left and right sides.
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length < 1) return false;
        return helper(nums, 0, nums.length - 1, target);
    }

    private boolean helper(int[] nums, int start, int end, int target) {
        if(start > end || start < 0 || end > nums.length - 1) return false;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) return true;
            if(nums[mid] > nums[end]) {
                //the mid point located in first sorted array.
                if(nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if(nums[mid] < nums[start]) {
                //the mid point sorted in second sorted array
                if(nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                //can't sure where the mid point located.
                return helper(nums, start, mid - 1, target) || (helper(nums, mid + 1, end, target));
            }
        }
        return (nums[start] == target || nums[end] == target);
    }
}
