package com.future.round2;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.

 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

 Example 1:
 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0
 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5

 * Created by someone on 10/10/17.
 */
public class Problem4 {
    /**
     * Analyze:
     * Basically, it's an issue similar with find Kth elements in two sorted array.
     * If the length is even, find the length / 2 -1 and length / 2.
     * If the length is odd, find the length / 2
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if(length % 2 == 0) {
            return (double)(findkth(nums1, 0, nums2, 0, length / 2) + findkth(nums1, 0, nums2, 0, length / 2 + 1)) / 2;
        } else {
            return (double)findkth(nums1, 0, nums2, 0, length / 2 + 1);
        }
    }

    /**
     * We can cut k / 2 each time.
     * nums1: 1, 3, 5, 7, 9
     * nums2: 2, 4, 6, 8
     * We want to find 5th. each array contribute 2 elements, nums1 contribute 1, 3, nums2 contribute 2, 4.
     * The 1, 3 must be in front of Kth.
     * if start1 >= nums1.length, return nums2[k]
     * @param nums1
     * @param start1
     * @param nums2
     * @param start2
     * @param k
     * @return
     */
    private static int findKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if(start1 >= nums1.length) return nums2[start2 + k - 1];

        if(start2 >= nums2.length) return nums1[start1 + k - 1];

        if(k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int mid1 = start1 + k / 2 < nums1.length ? nums1[start1 + k / 2 - 1] : nums1[nums1.length - 1];
        int mid2 = start2 + k / 2 < nums2.length ? nums2[start2 + k / 2 - 1] : nums2[nums2.length - 1];
        if(mid1 < mid2) {
            return findKth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
        } else {
            return findKth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
        }
    }

    private static int findkth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if(start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }

        if(start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }

        if(k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int val1 = start1 + k / 2 - 1>= nums1.length ? Integer.MAX_VALUE : nums1[start1 + k / 2 - 1];
        int val2 = start2 + k / 2 - 1>= nums2.length ? Integer.MAX_VALUE : nums2[start2 + k / 2 - 1];
        return val1 > val2 ? findkth(nums1, start1, nums2, start2 + k / 2, k - k / 2) :
                findkth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8}));
        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8}));
        System.out.println(findMedianSortedArrays(new int[]{1}, new int[]{2, 4, 6, 7, 8, 9, 10, 11, 12, 13}));
        System.out.println(findMedianSortedArrays(new int[]{1, 1, 1, 1}, new int[]{1, 1, 1}));
    }
}
