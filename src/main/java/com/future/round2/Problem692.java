package com.future.round2;

import java.util.*;

/**
 * Created by xingfeiy on 6/27/18.
 */
public class Problem692 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counter = new HashMap<>();
        for(String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() == o2.getValue()) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o2.getValue() - o1.getValue();
            }
        });
        for(Map.Entry<String, Integer> entry : counter.entrySet()) {
            queue.offer(entry);
            if(queue.size() > k) queue.poll();
        }
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) res.add(0, queue.poll().getKey());
        return res;
    }
}
