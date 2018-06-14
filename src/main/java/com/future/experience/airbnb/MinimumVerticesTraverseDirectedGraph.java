package com.future.experience.airbnb;

import java.util.*;

/**
 * Created by xingfeiy on 6/13/18.
 */
public class MinimumVerticesTraverseDirectedGraph {
    private void search(Set<Integer> res, Map<Integer, Set<Integer>> nodes,
                        int cur, int start,
                        Set<Integer> visited, Set<Integer> currVisited) {
        currVisited.add(cur);
        visited.add(cur);
        for (int next : nodes.get(cur)) {
            if (res.contains(next) && next != start) {
                res.remove(next);
            }
            if (!currVisited.contains(next)) {
                search(res, nodes, next, start, visited, currVisited);
            }
        }
    }
    public List<Integer> getMin(int[][] edges, int n) {
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodes.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            nodes.get(edge[0]).add(edge[1]);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                res.add(i);
                visited.add(i);
                search(res, nodes, i, i, visited, new HashSet<>());
            }
        }
        return new ArrayList<>(res);
    }

}
