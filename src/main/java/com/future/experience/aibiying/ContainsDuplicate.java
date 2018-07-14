package com.future.experience.aibiying;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by xingfeiy on 7/12/18.
 */
public class ContainsDuplicate {
    /**
     Given an array of integers, find if the array contains any duplicates.

     Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

     Example 1:

     Input: [1,2,3,1]
     Output: true
     Example 2:

     Input: [1,2,3,4]
     Output: false
     Example 3:

     Input: [1,1,1,3,3,4,3,2,4,2]
     Output: true
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        if(nums == null) return false;
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            if(!set.add(n)) return true;
        }
        return false;
    }

    /**
     Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
     such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

     Example 1:

     Input: nums = [1,2,3,1], k = 3
     Output: true
     Example 2:

     Input: nums = [1,0,1,1], k = 1
     Output: true
     Example 3:

     Input: nums = [1,2,3,1,2,3], k = 2

     Output: false
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                if(i - map.get(nums[i]) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }


    /**
     Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
     absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

     Example 1:

     Input: nums = [1,2,3,1], k = 3, t = 0
     Output: true
     Example 2:

     Input: nums = [1,0,1,1], k = 1, t = 2
     Output: true
     Example 3:

     Input: nums = [1,5,9,1,5,9], k = 2, t = 3
     Output: false
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || k < 0 || t < 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i]) && (i - map.get(nums[i]) <= k)) return true;
            Map.Entry<Integer, Integer> lowEntry = map.lowerEntry(nums[i]);
            while (lowEntry != null && ((long)nums[i] - (long)lowEntry.getKey() <= t)) {
                if(Math.abs(i - lowEntry.getValue()) <= k) return true;
                lowEntry = map.lowerEntry(lowEntry.getKey());
            }
            Map.Entry<Integer, Integer> highEntry = map.higherEntry(nums[i]);
            while (highEntry != null && ((long)highEntry.getKey() - (long)nums[i]) <= t) {
                if(Math.abs(i - highEntry.getValue()) <= k) return true;
                highEntry = map.higherEntry(highEntry.getKey());
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicate c = new ContainsDuplicate();
        System.out.println(c.containsDuplicate(new int[]{1, 2, 3, 4}));
        System.out.println(c.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(c.containsNearbyAlmostDuplicate(new int[]{1,0,1,1}, 1, 2));
        System.out.println(c.containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3));
        System.out.println(c.containsNearbyAlmostDuplicate(new int[]{7, 2, 8}, 2, 1));
    }
}
