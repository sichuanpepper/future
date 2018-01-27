package com.future.foundation;

import com.future.utils.DisplayUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are some common problems about the sub array sum.
 * 1. Find all sub arrays which sum up to given value.
 * 2. Find largest sub array which sum up to given value.
 * 3, Find the sub array which has largest sum.
 *
 * Created by someone on 10/31/17.
 */
public class SubarraySum {

//    public static List<List<Integer>> findAllSubarray(int[] nums, int target) {}

//    public static List<Integer> findLargestSubarray(int[] nums, int target) {}

    /**
     * Questions: Find the subarray which has maximum sum.
     * Test case: [-2, -1, -3] => -1
     * Test case: [1, 2, 3] => 6
     * Test case: [0, -3, 6, -2, 5] => 9
     * Analyze:
     * 1. Negative number makes the sum smaller, so if current sum is negative, it's useless for back forward subarray.
     * @param nums
     * @return
     */
    public static int findLargestSum(int[] nums) {
        if(nums == null || nums.length < 1) {
            return Integer.MIN_VALUE;
        }

        int sumHere = nums[0];
        int curMaxSum = nums[0];
        //it's wrong
//        for(int i = 1; i < nums.length; i++) {
//            sumHere = Math.max(0, sumHere + nums[i]);
//            curMaxSum = Math.max(sumHere, curMaxSum);
//        }
        return curMaxSum;
    }

    /**
     * Questions: Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.
     * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
       Ouptut: Sum found between indexes 2 and 4

       Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
       Ouptut: Sum found between indexes 1 and 4

       Input: arr[] = {1, 4}, sum = 0
       Output: No subarray found

     * Analyze:
     * For any integer array, the sum of subarray
     * sum(m, n) = sum(0, n) - sum(0, m - 1)
     * Likes array [1, 2, 3, 4], sum[1, 3, 6, 10]
     * sum(1, 3) = 10 - 1
     * @param nums
     * @param target
     * @return
     */
    public static int[] findSubarraySumTo(int[] nums, int target) {
        if(nums == null || nums.length < 1) return new int[2];
        Map<Integer, Integer> sumMap = new HashMap<>(); //key is the sum diff, value is index
        sumMap.put(0, -1);
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sumMap.containsKey(sum - target)) return new int[]{sumMap.get(sum - target) + 1, i};
            sumMap.put(sum, i);
        }
        return new int[2];
    }

    public static void main(String[] args) {
        DisplayUtils.printArray(findSubarraySumTo(new int[]{1, 2, 3, 4}, 9));
        DisplayUtils.printArray(findSubarraySumTo(new int[]{4, 1, 2, 6}, 3));
        DisplayUtils.printArray(findSubarraySumTo(new int[]{1, 4, 3, 8}, 6));
        DisplayUtils.printArray(findSubarraySumTo(new int[]{1, 4, 3, 1, 2, 3}, 5));
        DisplayUtils.printArray(findSubarraySumTo(new int[]{1, -4, 3, 1, 2, 3}, -1));
    }
}
