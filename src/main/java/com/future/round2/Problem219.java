package com.future.round2;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/description/
 *
 *
 Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

 * Created by xingfeiy on 12/12/17.
 */
public class Problem219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length < 2) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(i - map.getOrDefault(nums[i], -k - 1) <= k) return true;
            map.put(nums[i], i);
        }
        return false;
    }
}
