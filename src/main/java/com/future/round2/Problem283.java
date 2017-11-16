package com.future.round2;

import com.future.utils.DisplayUtils;

/**
 * https://leetcode.com/problems/move-zeroes/description/
 *
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.

 For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.

 * Created by someone on 11/14/17.
 */
public class Problem283 {

    /**
     * Analyze:
     * Use two pointers, one pointer points to the first insert position of non-zero
     * the second pointer keep traversing array to find the non-zero.
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length < 1) {
            return;
        }
        int p1 = 0;
        int p2 = 0;
        while (p2 < nums.length) {
            if(nums[p2] != 0) {
                nums[p1++] = nums[p2++];
            } else {
                p2++;
            }
        }
        for(int i = p1; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroesOpt(int[] nums) {
        if(nums == null || nums.length < 1) {
            return;
        }
        int insertPos = 0;
        for(int num : nums) {
            if(num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) nums[insertPos++] = 0;
    }

    public static void main(String[] args) {
        int[] nums = null;
        Problem283 p = new Problem283();
        //1. null array
        p.moveZeroes(nums);
        System.out.println((nums == null) ? "null" : "not null");
        //2. all elements are zero
        nums = new int[]{0, 0, 0};
        p.moveZeroes(nums);
        DisplayUtils.printArray(nums);

        //3. all elements are non-zero
        nums = new int[]{1, 2, 3};
        p.moveZeroes(nums);
        DisplayUtils.printArray(nums);

        //4. others
        nums = new int[]{0, 0, 1, 2, 0, 3, 0};
        p.moveZeroes(nums);
        DisplayUtils.printArray(nums);

        //1. null array
        p.moveZeroesOpt(nums);
        System.out.println((nums == null) ? "null" : "not null");
        //2. all elements are zero
        nums = new int[]{0, 0, 0};
        p.moveZeroesOpt(nums);
        DisplayUtils.printArray(nums);

        //3. all elements are non-zero
        nums = new int[]{1, 2, 3};
        p.moveZeroesOpt(nums);
        DisplayUtils.printArray(nums);

        //4. others
        nums = new int[]{0, 0, 1, 2, 0, 3, 0};
        p.moveZeroesOpt(nums);
        DisplayUtils.printArray(nums);
    }
}
