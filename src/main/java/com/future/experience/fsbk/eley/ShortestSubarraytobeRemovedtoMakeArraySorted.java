package com.future.experience.fsbk.eley;

/**
 * https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
 *
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Return the length of the shortest subarray to remove.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,10,4,2,3,5]
 * Output: 3
 * Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
 * Another correct solution is to remove the subarray [3,10,4].
 *
 * Thoughts:
 * - Find the non-decreasing sub array on both leftmost and rightmost, the mid sub array is what we need to delete.
 * - The tail of left might be larger than head of right, so we may still need to remove elements from tail of left and head of right.
 * - Use two pointers, one start from left and one start from right, move left make a minimal range to remove, move right makes a larger.
 *
 */
public class ShortestSubarraytobeRemovedtoMakeArraySorted {
    public int findLengthOfShortestSubarray(int[] arr) {
        int leftTail = 0, rightHead = arr.length - 1;
        while(leftTail < arr.length - 1 && arr[leftTail] <= arr[leftTail + 1]) {
            leftTail++;
        }

        if(leftTail == arr.length - 1) {
            return 0;
        }

        while(rightHead > 0 && arr[rightHead] >= arr[rightHead - 1]) {
            rightHead--;
        }

        int l = 0, r = rightHead, res = Math.min(arr.length - leftTail - 1, rightHead);
        while(l <= leftTail && r < arr.length) {
            if(arr[l] <= arr[r]) {
                res = Math.min(res, r - l - 1);
                l++;
            } else {
                r++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ShortestSubarraytobeRemovedtoMakeArraySorted p = new ShortestSubarraytobeRemovedtoMakeArraySorted();
        //[6,3,10,11,15,20,13,3,18,12]
        System.out.println(p.findLengthOfShortestSubarray(new int[]{6,3,10,11,15,20,13,3,18,12}));
    }
}
