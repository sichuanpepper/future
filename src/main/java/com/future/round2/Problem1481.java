package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
 */
public class Problem1481 {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b)->Integer.compare(a.getValue(), b.getValue()));
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }

        while(!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            if(entry.getValue() > k) {
                return queue.size() + 1;
            } else if(entry.getValue() < k) {
                k = k - entry.getValue();
            } else {
                return queue.size();
            }
        }
        return 0;
    }
}
