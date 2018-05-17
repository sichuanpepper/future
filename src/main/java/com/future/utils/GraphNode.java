package com.future.utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by xingfeiy on 5/16/18.
 */
public class GraphNode {
    public int val;
    public GraphNode[] neighbors;

    public GraphNode(int val) {
        this.val = val;
    }

    /**
     * https://www.geeksforgeeks.org/graph-and-its-representations/
     * @return
     */
    public static GraphNode sample1() {
        GraphNode node0 = new GraphNode(0);
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        node0.neighbors = new GraphNode[]{node1, node4};
        node1.neighbors = new GraphNode[]{node0, node2, node3};
        node2.neighbors = new GraphNode[]{node1, node3};
        node3.neighbors = new GraphNode[]{node1, node2, node4};
        node4.neighbors = new GraphNode[]{node0, node1, node3};
        return node0;
    }

    public static void dfs(GraphNode head) {
        if(head == null) return;
        dfsHelper(head, new HashSet<>());
    }

    private static void dfsHelper(GraphNode head, Set<GraphNode> visited) {
        if(head == null || visited.contains(head)) return;
        System.out.println(head.val);
        visited.add(head);
        if(head.neighbors != null) {
            for(GraphNode n : head.neighbors) {
                dfsHelper(n, visited);
            }
        }
    }

    public static void bfs(GraphNode head) {
        if(head == null) return;
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(head);
        Set<GraphNode> visited = new HashSet<>();
        visited.add(head);
        while (!queue.isEmpty()) {
            GraphNode tmp = queue.poll();
            System.out.println(tmp.val);
            if(tmp.neighbors != null) {
                for(GraphNode n : tmp.neighbors) {
                    if(!visited.contains(n)) {
                        queue.offer(n);
                        visited.add(n);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        dfs(sample1());
        System.out.println("====================");
        bfs(sample1());
    }
}
