package com.future.round2;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/description/
 *
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute
 * difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 *
 * Created by xingfeiy on 12/12/17.
 */
public class Problem220 {
    /**
     * Similar with problem 219, here we can use TreeMap.
     * There's a function in TreeMap which can get subMap view by given key range.
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length < 2 || t < 0 || k < 1) return false;
        TreeMap<Long, Integer> map = new TreeMap<>();
        for(int i = 0; i < nums.length; i++) {
            long start = Math.min((long)nums[i] - t, (long)nums[i] + t);
            long end = Math.max((long)nums[i] - t, (long)nums[i] + t) + 1;
            Map<Long, Integer> subMap = map.subMap(start, end);
            for(Map.Entry<Long, Integer> entry : subMap.entrySet()) {
                if(i - entry.getValue() <= k) return true;
            }
            map.put((long)nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        Problem220 p = new Problem220();
//        System.out.println(p.containsNearbyAlmostDuplicate(new int[]{0, 2147483647}, 1, 2147483647));
        System.out.println(p.containsNearbyAlmostDuplicate(new int[]{-1, -1}, 1, -1));
    }
}
