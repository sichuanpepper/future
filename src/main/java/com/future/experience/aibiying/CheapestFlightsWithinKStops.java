package com.future.experience.aibiying;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by xingfeiy on 6/25/18.
 */
public class CheapestFlightsWithinKStops {
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


    /**
     * There are n cities which represented by 0 to n-1
     * We are given a flights info represented by a 2d array, each row includes 3 elements, departure, destination, price.
     * And we are going to find the cheapest flight tickets from src to dst with at most k stops.
     *
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPriceMy(int[][] flights, int src, int dst, int K, int n) {
        return 0;
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops c = new CheapestFlightsWithinKStops();
//        int[][] flights = new int[][]{{1, 2, 100}, {2, 3, 150}, {1, 3, 300}, {3, 4, 100}, {2, 4, 200}, {1, 4, 500}};
        int[][] flights = new int[][]{{0, 1, 100}, {1, 9, 800}, {0, 5, 300}, {5, 6, 100}, {6, 9, 100}};
        System.out.println(c.findCheapestPrice(10, flights, 0, 9, 4));
    }
}
