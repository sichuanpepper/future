package com.future.round2;

import java.util.*;

/**
 * [tags] Facebook
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 * Given a non-empty array of integers, return the k most frequent elements.

 For example,
 Given [1,1,1,2,2,3] and k = 2, return [1,2].

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 * Created by someone on 11/30/17.
 */
public class Problem347 {
    /**
     * Analyze:
     * We can use a map to store the element and its frequency, and then sort it by value.
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });


        List<Integer> res = new ArrayList<>(k);
        for(int i = 0; i < k; i++) {
            res.add(list.get(i).getKey());
        }
        return res;
    }

    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums) map.put(n , map.getOrDefault(n, 0) + 1);
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> (o1.getValue() - o2.getValue()));
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
            if(queue.size() > k) queue.poll();
        }
        List<Integer> res = new ArrayList<>(k);
        for(int i = 0; i < k; i++) {
            res.add(queue.poll().getKey());
        }
        return res;
    }
}
