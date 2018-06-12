package com.future.round2;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
 *
 The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 The size of flights will be in range [0, n * (n - 1) / 2].
 The format of each flight will be (src, dst, price).
 The price of each flight will be in the range [1, 10000].
 k is in the range of [0, n - 1].
 There will not be any duplicated flights or self cycles.

 * Created by xingfeiy on 6/11/18.
 */
public class Problem787 {
    /**
     *
     * @param n
     * @param flights [[0,1,100],[1,2,100],[0,2,500]]
     * @param src
     * @param dst
     * @param K
     * @return
     *
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if(flights == null || flights.length < 1) return -1;
        if(src == dst) return 0;

        //key -> city, (key->other city, value->price)
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] flight : flights) {
            Map<Integer, Integer> subMap = map.getOrDefault(flight[0], new HashMap<>());
            subMap.put(flight[1], flight[2]);
            map.put(flight[0], subMap);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((obj1, obj2) -> (obj1[1] - obj2[1]));

        queue.offer(new int[]{src, 0, K + 1});

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            if(tmp[0] == dst) return tmp[1];
            if(tmp[2] > 0 && map.containsKey(tmp[0])) {
                for(Map.Entry<Integer, Integer> entry : map.get(tmp[0]).entrySet()) {
                    queue.offer(new int[]{entry.getKey(), entry.getValue() + tmp[1], tmp[2] - 1});
                }
            }

        }

        return -1;
    }
}
