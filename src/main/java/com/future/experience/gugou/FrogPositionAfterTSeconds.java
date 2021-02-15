package com.future.experience.gugou;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/frog-position-after-t-seconds/
 */
public class FrogPositionAfterTSeconds {
    /**
     * Build tree, it's undirected tree.
     * There's one and only one valid tree, no graph
     * Tree root is always 1
     * t seconds limited, the frog may pass by the target, so stop if the node is leaf or t equals 0
     * @param n
     * @param edges
     * @param t
     * @param target
     * @return
     */
    public double frogPosition(int n, int[][] edges, int t, int target) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for(int[] edge : edges) {
            List<Integer> children = tree.getOrDefault(edge[0], new ArrayList<>());
            children.add(edge[1]);
            tree.put(edge[0], children);

            children = tree.getOrDefault(edge[1], new ArrayList<>());
            children.add(edge[0]);
            tree.put(edge[1], children);
        }

        Queue<Node> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(new Node(1, 1.0));
        while(!queue.isEmpty() && t >= 0) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();

                visited.add(node.index);
                List<Integer> children = tree.getOrDefault(node.index, new ArrayList<>())
                        .stream()
                        .filter(obj -> !visited.contains(obj))
                        .collect(Collectors.toList());
                if(node.index == target && (t == 0 || children.size() == 0)) {
                    return node.p;
                }
                for(int child : children) {
                    queue.offer(new Node(child, node.p / (double)children.size()));
                }
            }
            t--;
        }
        return 0;
    }

    private class Node {
        public int index;

        public double p;

        public Node(int index, double p) {
            this.index = index;
            this.p = p;
        }
    }
}
