package com.future.experience.fsbk;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * Analyze:
 * Find the first non-negative number n, and then use two pointer, one start from n - 1 to left, and other one start from n to right.
 */
public class SquaresofaSortedArray {
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int pivot = 0;
        while(pivot < nums.length && nums[pivot] < 0) {
            pivot++;
        }
        int p1 = pivot - 1, i = 0;
        while(p1 >= 0 || pivot < nums.length) {
            int left = (p1 >= 0) ? nums[p1] * nums[p1] : Integer.MAX_VALUE;
            int right = (pivot < nums.length) ? nums[pivot] * nums[pivot] : Integer.MAX_VALUE;
            if(left < right) {
                res[i++] = left;
                p1--;
            } else {
                res[i++] = right;
                pivot++;
            }
        }
        return res;
    }
}
