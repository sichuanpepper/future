package com.future.experience.gugou;

import java.util.*;

/**
 * 蠡口克隆图 如何大规模的测试. 你的这个图是真的 深度克隆
 * https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * Traversal the graph, clone node one by one.
     * To traversal the graph, we can do either bfs and dfs.
     * We may visit same node more than one times, in this case, we should return a node that has been created before, we need to know the mapping.
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        return helper(node, new HashSet<>(), new HashMap<Node, Node>());
    }

    private Node helper(Node node, Set<Node> visited, HashMap<Node, Node> map) {
        if(node == null) return null;
        if(visited.contains(node)) return map.get(node);
        visited.add(node);
        Node newNode = new Node(node.val);
        map.put(node, newNode);
        for(Node neighbor : node.neighbors) {
            newNode.neighbors.add(helper(neighbor, visited, map));
        }

        return newNode;
    }


    /**
     * In the BFS traversal, it's easy to copy the val, the hard part is copy the neighbors.
     * When we visit a node, first thing is get its copy, and then go through its neighbors,
     *  - The neighbor might be visited and exited in the map since it's a graph, in this case just get the copy and add back to the corresponding copied parent.
     *  - If the neighbor doesn't exist in the map, which means this node is not visited yet, add it to queue, and make a copy then add back to copied parent.
     * @param node
     * @return
     */
    public Node cloneGraphBFS(Node node) {
        if(node == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        Map<Node, Node> map = new HashMap<>();
        Node copyNode = new Node(node.val);
        map.put(node, copyNode);

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            //make a copy
            Node copy = map.get(n);

            for(Node child : n.neighbors) {
                Node copyChild = map.get(child);
                if(copyChild == null) {
                    copyChild = new Node(child.val);
                    queue.offer(child);
                    map.put(child, copyChild);
                }
                copy.neighbors.add(copyChild);
            }
        }
        return copyNode;
    }

}
