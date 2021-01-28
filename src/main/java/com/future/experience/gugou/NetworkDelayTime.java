package com.future.experience.gugou;

import java.util.*;

/**
 * https://leetcode.com/problems/network-delay-time/
 */
public class NetworkDelayTime {
    /**
     * Similar as the cheapest flight ticket.
     *
     * Each node could be reached by zero or one or multiple ways, so basically we should calculate the fastest way for each node,
     * and then return the maximum time of all nodes.
     *
     * So we can use a priority queue which sorts by reach time in ascending way.
     * Since it's a graph, we use a set to maintain the visited nodes.
     * Since the priority queue is sorted by reach time in ascending way, so the last one must be the maximum one, the final result.
     *
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        //key is the node, value is the list of children, int[0] dest node, int[1] distance
        Map<Integer, List<int[]>> graph = new HashMap<>();
        //build graph
        for(int[] edge : times) {
            List<int[]> val = graph.getOrDefault(edge[0], new ArrayList<>());
            val.add(new int[]{edge[1], edge[2]});
            graph.put(edge[0], val);
        }

        //int[0] distance, int[1] node
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0]));
        queue.offer(new int[]{0, K});
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            if(visited.contains(node[1])) continue;
            visited.add(node[1]);
            res = node[0];
            if(graph.containsKey(node[1])) {
                for(int[] edge : graph.get(node[1])) {
                    queue.offer(new int[]{res + edge[1], edge[0]});   //time complexity: olog(n)
                }

            }
        }
        return (visited.size() == N) ? res : -1;

    }
}
