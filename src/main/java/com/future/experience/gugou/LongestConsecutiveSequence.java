package com.future.experience.gugou;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {

    /**
     * - Unsorted
     * - Has duplicated?
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        //key: num, value: length of consecutive squence.
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int num : nums) {
            if(map.containsKey(num)) continue;
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            map.put(num, left + right + 1);
            max = Math.max(max, left + right + 1);
            map.put(num - left, left + right + 1);
            map.put(num + right, left + right + 1);
        }
        return max;
    }

    public int longestConsecutive2(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        //key: num, value: length of consecutive squence.
        Set<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);
        int max = 0;
        for(int num : nums) {
            if(set.contains(num)) {
                int right = num, left = num;
                while(set.contains(--left)) set.remove(left);
                while(set.contains(++right)) set.remove(right);
                max = Math.max(max, right - left - 1);
            }
        }
        return max;
    }
}
