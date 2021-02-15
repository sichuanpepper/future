package com.future.round2;

/*
https://leetcode.com/problems/subarray-product-less-than-k/
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.
 */
public class Problem713 {
    /**
     * It's a subarrays problem, one straightforward in my mind is sliding window, we need two pointers, left and right.
     * We keep moving right pointer and maintaining a subarray where product is less than k,
     * - each right pointer moving will generate a new subarray,
     *  - if the product of new subarray is less than k, this new subarray generates (right - left + 1) sub-subarrays.
     *  - else, move left pointer util the product of subarray is less than k or left equals right.
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k == 0) return 0;
        int left = 0, right = 0, counter = 0;
        long prod = 1;

        while(right < nums.length) {
            prod *= nums[right];
            while(left <= right && prod >= k) {
                prod /= nums[left++];
            }
            counter += right - left + 1;
            right++;
        }
        return counter;
    }
}
