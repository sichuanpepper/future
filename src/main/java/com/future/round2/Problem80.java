package com.future.round2;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
 *
 * Follow up for "Remove Duplicates":
 What if duplicates are allowed at most twice?

 For example,
 Given sorted array nums = [1,1,1,2,2,3],

 Your function should return length = 5, with the first five elements of nums being
 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 * Created by xingfeiy on 12/11/17.
 */
public class Problem80 {
    public int removeDuplicates(int[] nums) {
        if(nums == null) return 0;
        if(nums.length < 3) return nums.length;
        int p1 = 0;
        int p2 = 2;
        while (p2 < nums.length) {
            if(nums[p1] != nums[p2]) {
                nums[p1 + 2] = nums[p2];
                p1++;
            }
            p2++;
        }
        return p1 + 2;
    }
}
