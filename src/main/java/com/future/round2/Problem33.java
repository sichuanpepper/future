package com.future.round2;

/**
 *https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 *
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.

 * Created by xingfeiy on 12/17/17.
 */
public class Problem33 {
    /**
     * Analyze:
     * It's an issue about array, so it's better to know first:
     *  - Sorted Array in ascending order.
     *  - No Duplicate.
     *  - The target may exist or may not.
     *
     *  Search in sorted array, think about binary search first.
     *  Actually we have one or two sorted arrays here.
     *   - One sorted array, no rotation or full rotation, that's binary search.
     *      - if array[mid] > target, end <- mid - 1
     *      - else start <- mid + 1
     *   - Two sorted array, just as the example above.
     *      - if array[mid] > array[end], the mid located in first sorted array.
     *          - if array[mid] > target,
     *              if array[start] < target, end <- mid - 1
     *              else start <- mid + 1
     *          - else start <- mid + 1
     *      - else, the mid located in second sorted array.
     *          - if array[mid] < target
     *              - if array[end] > target, start <- mid + 1
     *              - else end <- mid - 1
     *          - else end <- mid - 1
     *
     *  Time Complexity: O(lgn)
     *  Space Complexity: O(1)
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums == null || nums.length < 1) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start  + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                return mid;
            }

            //located the mid point
            if(nums[mid] > nums[end]) {
                // in the first sorted array.
                if(nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                //in the second sorted array
                if(nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        if(nums[start] == target) return start;
        return nums[end] == target ? end : -1;
    }
}
