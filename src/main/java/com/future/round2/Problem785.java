package com.future.round2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * https://leetcode.com/problems/is-graph-bipartite/description/

 Given an undirected graph, return true if and only if it is bipartite.

 Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

 The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

 Example 1:
 Input: [[1,3], [0,2], [1,3], [0,2]]
 Output: true
 Explanation:
 The graph looks like this:
 0----1
 |    |
 |    |
 3----2
 We can divide the vertices into two groups: {0, 2} and {1, 3}.
 Example 2:
 Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 Output: false
 Explanation:
 The graph looks like this:
 0----1
 | \  |
 |  \ |
 3----2
 We cannot find a way to divide the set of nodes into two independent subsets.

 Note:

 graph will have length in range [1, 100].
 graph[i] will contain integers in range [0, graph.length - 1].
 graph[i] will not contain i or duplicate values.
 The graph is undirected: if any element j is in graph[i], then i will be in graph[j].

 * Created by xingfeiy on 4/16/18.
 */
public class Problem785 {
    /**
     * Analyze:
     * The simple way is BFS, traversal nodes layer by layer.
     * - Start from a node which has neighbor (the node may doesn't have any neighbor), and set its subset as 1.
     * - Traversal all neighbors,
     *      - If neighbor is not visited yet, add it into queue.
     *      - If the neighbor has same subset number, that violated the rule, return false;
     *      - If the neighbor has different subset number(0 or opposite number), set the neighbor's subset number as opposite number.
     *
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        //0: not visited, 1: visited and in subset 1, -1: visited and in subset -1
        int[] visited = new int[graph.length];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < graph.length; i++) {
            if(graph[i] == null || graph[i].length < 1 || visited[i] != 0) continue;
            queue.offer(i);
            visited[i] = 1;
            while(!queue.isEmpty()) {
                int cur = queue.poll();
                for(int neighbor : graph[cur]) {
                    if(visited[neighbor] == 0) queue.offer(neighbor);
                    if(visited[neighbor] == visited[cur]) return false;
                    visited[neighbor] = -visited[cur];
                }
            }
        }

        return true;
    }
}
