package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.stream.StreamSupport;

/**
 * https://leetcode.com/problems/merge-sorted-array/description/
 *
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:
 You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 The number of elements initialized in nums1 and nums2 are m and n respectively.
 *
 * Created by xingfeiy on 11/21/17.
 */
public class Problem88 {
    /**
     * Analyze:
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        while (n > 0) {
            if(m > 0 && (nums1[m - 1] > nums2[n - 1])) {
                nums1[index--] = nums1[m-- - 1];
            } else {
                nums1[index--] = nums2[n-- - 1];
            }
        }
    }

    public static void main(String[] args) {
        Problem88 p = new Problem88();
        int[] nums1 = new int[]{1, 3, 5, 0, 0, 0};
        int[] nums2 = new int[]{2, 4, 6};
        p.merge(nums1, 3, nums2, 3);
        DisplayUtils.printArray(nums1);

        nums1 = new int[]{1, 0, 0, 0, 0, 0};
        nums2 = new int[]{2, 4, 6};
        p.merge(nums1, 1, nums2, 3);
        DisplayUtils.printArray(nums1);
    }
}
