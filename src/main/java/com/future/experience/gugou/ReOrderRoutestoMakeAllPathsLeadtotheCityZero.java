package com.future.experience.gugou;

import java.util.*;

/**
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 */
public class ReOrderRoutestoMakeAllPathsLeadtotheCityZero {
    /**
     * Just imagine if it's a tree, we have all ways from parent to child, how many ways need to reorder?
     * It's simple to get the number by either BFS or DFS.
     *
     * The only difference in this problem is it's not single way, so we just need a flag to represent the direction, let's say in and out.
     * if it's out, means it need to reorder.
     * @param n
     * @param connections
     * @return
     */
    public int minReorder(int n, int[][] connections) {
        //asmue the inputs are valid
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for(int[] conn : connections) {
            int from = conn[0], to = conn[1];
            List<Integer> list = tree.getOrDefault(from, new ArrayList<>());
            list.add(-to);
            tree.put(from, list);
            list = tree.getOrDefault(to, new ArrayList<>());
            list.add(from);
            tree.put(to, list);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        int cnt = 0;
        while(!queue.isEmpty()) {
            int node = queue.poll();
            if(node < 0) {
                cnt++;
            }
            node = Math.abs(node);
            visited.add(node);
            if(tree.containsKey(node)) {
                for(int child : tree.get(node)) {
                    if(visited.contains(Math.abs(child))) {
                        continue;
                    }
                    queue.offer(child);
                }
            }

        }
        return cnt;
    }
}
