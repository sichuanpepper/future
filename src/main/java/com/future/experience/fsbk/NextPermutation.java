package com.future.experience.fsbk;

import com.future.utils.DisplayUtils;

/**
 * https://leetcode.com/problems/next-permutation/submissions/
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        //find the pivot that breaks the non-increasing suffix
        int p = nums.length - 2;
        while(p >= 0) {
            if(nums[p] >= nums[p + 1]) {
                p--;
            } else {
                break;
            }
        }

        if(p < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int p1 = nums.length - 1;
        while(nums[p1] <= nums[p]) {
            p1--;
        }
        int tmp = nums[p];
        nums[p] = nums[p1];
        nums[p1] = tmp;
        reverse(nums, p + 1, nums.length - 1);

    }

    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        NextPermutation p = new NextPermutation();
        int[] nums = new int[]{1, 5, 1};
        p.nextPermutation(nums);
        DisplayUtils.printArray(nums);
    }
}
