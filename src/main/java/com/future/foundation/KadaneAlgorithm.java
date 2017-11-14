package com.future.foundation;

/**
 * Kadane Algorithm is an algorithm to solve the maximum subarray problem that is the task of finding the
 * contiguous subarray within a one-dimensional array of numbers which has the largest sum.
 *
 * Example:
    int [] A = {−2, 1, −3, 4, −1, 2, 1, −5, 4};
    Output: contiguous subarray with the largest sum is 4, −1, 2, 1, with sum 6.

 start:
    max_so_far = 0
     max_ending_here = 0

 loop i= 0 to n
    (i) max_ending_here = max_ending_here + a[i]
    (ii) if(max_ending_here < 0)
        max_ending_here = 0
    (iii) if(max_so_far < max_ending_here)
        max_so_far = max_ending_here
 return max_so_far

 * Created by someone on 10/30/17.
 */
public class KadaneAlgorithm {
    /**
     * This solution works fine if there's at one element is non-negative.
     * @param nums
     * @return
     */
    public static int maxSum(int[] nums) {
        if(nums == null || nums.length < 1) {
            return Integer.MIN_VALUE;
        }

        int maxSoFar = 0;
        int maxHere = 0;
        for(int num : nums) {
            maxHere += num;
            if(maxHere < 0) {
                maxHere = 0;
            } else {
                maxSoFar = Math.max(maxSoFar, maxHere);
            }
        }
        return maxSoFar;
    }

    public static int maxSum2(int[] nums) {
        if(nums == null || nums.length < 1) {
            return Integer.MIN_VALUE;
        }

        int maxSoFar = nums[0];
        int maxHere = nums[0];
        for(int i = 1; i < nums.length; i++) {
            maxHere = Math.max(maxHere, maxHere + nums[i]);
            maxSoFar = Math.max(maxHere, maxSoFar);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{-2, 3, 0, -2, 4}));
        System.out.println(maxSum(new int[]{-2, -3, -2, -4}));
    }

}
