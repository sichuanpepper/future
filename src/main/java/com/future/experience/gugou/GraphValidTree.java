package com.future.experience.gugou;

import java.util.*;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 * For example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0]
 * and thus will not appear together in edges.
 */
public class GraphValidTree {
    /**
     * If it's a valid tree, then all nodes can be visited by tree traversal, so we can do either BFS or DFS
     * - If the count of visited node smaller than n, return false;
     * - Since we are given undirected edges, even if it's a valid tree, each child may visits a node that already be visited before,
     *   that node is its parent, so we just need to keep the parent info with node, then check if the revisited node is parent or not.
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        //build tree, index is the node index and value is the children.
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        //int[] {index, parent}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, -1});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            visited.add(node[0]);
            for(int child : tree.get(node[0])) {

                if(visited.contains(child)) {
                    if(child != node[1]) {
                        return false;
                    }
                } else {
                    queue.offer(new int[]{child, node[0]});
                }

            }
        }
        return visited.size() == n;
    }

    public static void main(String[] args) {
        GraphValidTree p = new GraphValidTree();
        System.out.println(p.validTree(5, new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 3}, new int[]{1, 4}})); //true
        System.out.println(p.validTree(5, new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 3}})); //false
        System.out.println(p.validTree(5, new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 3}, new int[]{1, 4}, new int[]{2, 3}})); //false
    }
}
