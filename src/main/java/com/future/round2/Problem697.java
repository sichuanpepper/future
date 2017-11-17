package com.future.round2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingfeiy on 11/15/17.
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
