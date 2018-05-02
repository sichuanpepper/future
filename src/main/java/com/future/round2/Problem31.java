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
     * - Ascending array, switch last two elements.
     * - Descending array, reverse it.
     *
     * so the solution could be, find a position where an ascending subarray ended, that means, from end of array, find
     * i where nums[i] > nums[i - 1], and it's easy to know the subarray from i to end is descending, so we need to find
     * the first element in desending subarray which greater than or equal to nums[i - 1], and switch them.
     * last thing, switch the desending subarray.
     *
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
