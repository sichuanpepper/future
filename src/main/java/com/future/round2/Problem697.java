package com.future.round2;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/degree-of-an-array/description/

 Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

 Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

 Example 1:
 Input: [1, 2, 2, 3, 1]
 Output: 2
 Explanation:
 The input array has a degree of 2 because both elements 1 and 2 appear twice.
 Of the subarrays that have the same degree:
 [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 The shortest length is 2. So return 2.
 Example 2:
 Input: [1,2,2,3,1,4,2]
 Output: 6
 Note:

 nums.length will be between 1 and 50,000.
 nums[i] will be an integer between 0 and 49,999.

 * Created by someone on 11/15/17.
 */
public class Problem697 {
    public int findShortestSubArray(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        int minLength = 1;
        int maxDegree = 0;
        Map<Integer, Integer> positionMap = new HashMap<>();
        Map<Integer, Integer> degreeMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(degreeMap.containsKey(nums[i])) {
                if(degreeMap.get(nums[i]) + 1 > maxDegree) {
                    //find a new max degree, set the minimum length as the length of this sub array.
                    minLength = i - positionMap.get(nums[i]) + 1;
                    maxDegree = degreeMap.get(nums[i]) + 1;
                } else if(degreeMap.get(nums[i]) + 1 == maxDegree) {
                    //find another max degree, check if this sub array is shorter.
                    minLength = Math.min(minLength, i - positionMap.get(nums[i]) + 1);
                }
                //set new degree for nums[i]
                degreeMap.put(nums[i], degreeMap.get(nums[i]) + 1);
            } else {
                degreeMap.put(nums[i], 1);
            }
            if(!positionMap.containsKey(nums[i])) {
                positionMap.put(nums[i], i);
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        Problem697 p = new Problem697();
        System.out.println(p.findShortestSubArray(new int[]{2,1,1,2,1,3,3,3,1,3,1,3,2}));
    }
}
