package com.future.round2;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 *
 * Created by xingfeiy on 11/26/17.
 */
public class Problem26 {

    /**
     * Sorted array and do it in-place.
     * Use two pointer, one pointer always points the final last position, and other one is keep traversing array.
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        int p1 = 0, p2 = 1;
        while (p2 < nums.length) {
            if(nums[p1] != nums[p2]) {
                nums[++p1] = nums[p2];
            }
            p2++;
        }
        return p1 + 1;
    }
}
