package com.future.round2;

/**
 * https://leetcode.com/problems/next-permutation/description/
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1

 * Created by someone on 11/26/17.
 */
public class Problem31 {
    /**
     * Analyze:
     * - sored in ascending, 1, 2, 3, 4, reverse last two elements.
     * - sored in descending, 4, 3, 2, 1, then reverse whole array.
     * - others, 1, 3, 2, 5, 4 -> 1, 3, 5, 2, 4
     *  -- from right side to left side, find the k element, which nums[k] > nums[k - 1], and swap it??
     *    --- 1, 3, 2, 5, 1 -> 1, 3, 5, 1, 2  - swap and reverse the right partition
     *    --- 1, 5, 4 -> 4, 1, 5 - after swap, we comes out 5, 1, 4, so we can't just swap it simply.
     *    --- The solution could be find the smallest element in range k to n - 1 which is greater than element k - 1.
     *    --- 1, 5, 4, find k = 1, and the smallest element in range 1 to 2 which is is greater than k - 1(here is 1) is 4, swap it.
     *    --- 1, 5, 4, 3 -> 3, 5, 4, 1 -> 3, 1, 4, 5
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) return;
        //find kth element
        int index = nums.length - 1;
        while (index > 0) {
            if(nums[index] > nums[index - 1]) break;
            index--;
        }
        //if it ordered in descending
        if(index == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        //find the smallest element in range k to n
        int end = nums.length - 1;
        while (end >= index) {
            if(nums[end] > nums[index - 1]) break;
            end--;
        }
        swap(nums, index - 1, end);

        reverse(nums, index, nums.length - 1);
    }

    private void swap(int[] nums, int from, int to) {
        int tmp = nums[from];
        nums[from] = nums[to];
        nums[to] = tmp;
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
