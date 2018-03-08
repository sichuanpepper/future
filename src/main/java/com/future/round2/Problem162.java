package com.future.round2;

/**
 * https://leetcode.com/problems/find-peak-element/description/
 * A peak element is an element that is greater than its neighbors.

 Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 You may imagine that num[-1] = num[n] = -∞.

 For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * Created by xingfeiy on 3/7/18.
 */
public class Problem162 {
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length < 1) return Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            boolean largeThanPre = (i == 0) || nums[i] > nums[i - 1];
            boolean largeThanNext = (i == nums.length - 1) || nums[i] > nums[i + 1];
            if(largeThanPre && largeThanNext) return i;
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Use binary search to improve performance.
     * for any element in index i, if
     *  - nums[i-1] < nums[i] > nums[i+1], i is a peak.
     *  - nums[i-1] < nums[i] < nums[i+1], the right side must have a peak, if it's ascending sorted, the peak is last one.
     *  - nums[i-1] > nums[i] > nums[i+1], the left side must have a peak.
     *  - nums[i-1] > nums[i] < nums[i+1], both sides have at least one peak.
     * @param nums
     * @return
     */
    public int findPeakElementV2(int[] nums) {
        if(nums == null || nums.length < 1) return Integer.MIN_VALUE;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if(nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return nums[start] > nums[end] ? start : end;
    }
}
