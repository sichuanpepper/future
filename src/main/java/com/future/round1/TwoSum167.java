package com.future.round1;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

 The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

 You may assume that each input would have exactly one solution and you may not use the same element twice.

 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2

 * Created by someone on 4/10/17.
 */
public class TwoSum167 {
    public int[] twoSum(int[] numbers, int target) {
        if(numbers == null || numbers.length < 2) {
            return new int[2];
        }
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int v = numbers[start] + numbers[end];
            if(v == target) {
                return new int[]{start + 1, end + 1};
            } else if(v > target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[2];
    }
}
