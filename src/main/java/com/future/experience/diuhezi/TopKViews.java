package com.future.experience.diuhezi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 Given a non-empty array of integers, return the k most frequent elements.

 For example,
 Given [1,1,1,2,2,3] and k = 2, return [1,2].

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 * Created by xingfeiy on 7/20/18.
 */
public class TopKViews {

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        //key is element, value is counter
        Map<Integer, Integer> counter = new HashMap<>();
        for(int n : nums) counter.put(n, counter.getOrDefault(n, 0) + 1);

        for(Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if(buckets[entry.getValue()] == null) buckets[entry.getValue()] = new ArrayList<>();
            buckets[entry.getValue()].add(entry.getKey());
        }

        List<Integer> res = new ArrayList<>();
        for(int i = nums.length; i >= 0 && res.size() < k; i--) {
            if(buckets[i] != null) res.addAll(buckets[i]);
        }
        return res;
    }

//    private List<Integer>[] buckets = new ArrayList[100];
//    private Map<Integer, Integer> counter = new HashMap<>();
//    private int highestFreq = 0;
//    public List<Integer> addFrequent(int n) {
//
//    }

}
