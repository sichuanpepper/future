package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

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


    /**
     * Assumptions:
     *  - has cycle
     *  - a node may not have any edge
     *  - may have multiple groups
     *  - may have different combinations, return one of them is fine
     *
     * Analyze:
     *  - Traversal nodes from 0 to n-1
     *      - if the node has been visited, continue to next
     *      - put current node into res
     *      - do DFS/BFS for current node, record current path.
     *          - if node already in path(visited), continue to next
     *          - if the node existed in res, remove it from res.
     * @param edges
     * @param n
     * @return
     */
    public List<Integer> mySolution(int[][] edges, int n) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int i = 0; i < n; i++) graph.put(i, new HashSet<>());
        for(int[] edge : edges) graph.get(edge[0]).add(edge[1]);

        Set<Integer> res = new HashSet<>();
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            res.add(i);
            //do dfs for i
            helper(graph, i, visited, new boolean[n], res);
        }
        return new ArrayList<>(res);
    }

    private void helper(Map<Integer, Set<Integer>> graph, int i, boolean[] visited, boolean[] curVisited, Set<Integer> res) {
        visited[i] = true;
        curVisited[i] = true;
        for(int neighbor : graph.get(i)) {
            if(curVisited[neighbor]) continue;
            if(res.contains(neighbor)) res.remove(neighbor);
            curVisited[neighbor] = true;
            helper(graph, neighbor, visited, curVisited, res);
        }
    }

    public static void main(String[] args) {
        MinimumVerticesTraverseDirectedGraph p = new MinimumVerticesTraverseDirectedGraph();
        int[][] edges = new int[][]{{0,0},{9, 9},{9, 2}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {2, 3}, {2, 6}};
        DisplayUtils.printList(p.getMin(edges, 10));
        DisplayUtils.printList(p.mySolution(edges, 10));
        edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {3, 1}, {3, 2}};
        DisplayUtils.printList(p.getMin(edges, 4));
        DisplayUtils.printList(p.mySolution(edges, 4));

        edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}};
        DisplayUtils.printList(p.getMin(edges, 4));
        DisplayUtils.printList(p.mySolution(edges, 4));

        edges = new int[][]{{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
        DisplayUtils.printList(p.getMin(edges, 4));
        DisplayUtils.printList(p.mySolution(edges, 4));
    }
}
