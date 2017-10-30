package com.future.round1;


/**
 * Created by someone on 7/11/17.
 */
public class P4 {
    public static int findkth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if(start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }

        if(start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }

        if(k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int val1 = start1 + k / 2 >= nums1.length ? Integer.MAX_VALUE : nums1[start1 + k / 2];
        int val2 = start2 + k / 2 >= nums2.length ? Integer.MAX_VALUE : nums2[start2 + k / 2];

        return val1 > val2 ? findkth(nums1, start1, nums2, start2 + k / 2, k - k / 2) :
                findkth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
    }

    public static int findkth2(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if(start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }

        if(start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }

        if(k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int val1 = start1 + k / 2 - 1 >= nums1.length ? Integer.MAX_VALUE : nums1[start1 + k / 2 - 1];
        int val2 = start2 + k / 2 - 1 >= nums2.length ? Integer.MAX_VALUE : nums2[start2 + k / 2 - 1];
        return val1 > val2 ? findkth2(nums1, start1, nums2, start2 + k / 2, k - k / 2) :
                findkth2(nums1, start1 + k / 2, nums2, start2, k - k / 2);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 5, 6};
        int[] nums2 = new int[]{2, 4, 6, 9};
        System.out.println(findkth(nums1, 0, nums2, 0, 3));
        System.out.println(findkth(nums1, 0, nums2, 0, 5));
        System.out.println(findkth(nums1, 0, nums2, 0, 1));
        System.out.println(findkth(nums1, 0, nums2, 0, 8));

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println(findkth2(nums1, 0, nums2, 0, 3));

    }
}
