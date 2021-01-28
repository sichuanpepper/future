package com.future.experience.gugou;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=675473&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
 * 一开始问知不知道graph，我说了下定义和各种graph，他要我用code写一个图的定义，我给了两种方法，
 * 他说好吧，我们就用第二种。然后coding是序列化和反序列化一个图。边写边讨论解释，最后表示满意，还剩十来分钟问他问题。
 */
public class SerializeGraph {
    public static class GraphNode {
        public int val;

        public List<GraphNode> neighbors;

        public GraphNode() {
            this.neighbors = new ArrayList<>();
            this.val = Integer.MIN_VALUE;
        }

        public GraphNode(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    /**
     * We can use an array to represent a not, the first element is its value, from 2 to end are neighbors.
     * //each node has unique value????
     * {[1, 2, 3]; [2, 1, 4];...}
     *
     * Be careful, where to check the visited...
     * @param node
     * @return
     */
    public String serialize(GraphNode node) {
        if(node == null) {
            return "";
        }
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        Set<GraphNode> visited = new HashSet<>();
        List<String> tokens = new ArrayList<>();
        while (! queue.isEmpty()) {
            GraphNode n = queue.poll();
            if(visited.contains(n)) {
                continue;
            }
            String[] values = new String[n.neighbors.size() + 1];
            int index = 0;
            values[index++] = Integer.toString(n.val);
            visited.add(n);
            for(GraphNode child : n.neighbors) {
                values[index++] = Integer.toString(child.val);
                if(!visited.contains(child)) {
                    queue.offer(child);
                }
            }
            tokens.add("[" + String.join( ",", values) + "]");
        }
        return "{" + String.join(";", tokens) + "}";
    }

    public GraphNode deserialzie(String str) {
        if(str == null || str.length() < 2) {
            return null;
        }

        GraphNode graph = null;
        Map<Integer, GraphNode> map = new HashMap<>();
        String[] nodeStrs = str.substring(1, str.length() - 1).split(";");
        for(String nodeStr : nodeStrs) {
            int[] values = strToIntArray(nodeStr);
            int nodeVal = values[0];
            GraphNode node = map.getOrDefault(nodeVal, new GraphNode());
            node.val = nodeVal;
            if(graph == null) {
                graph = node;
            }
            map.put(nodeVal, node);
            for(int i = 1; i < values.length; i++) {
                GraphNode child = map.getOrDefault(values[i], new GraphNode());
                node.neighbors.add(child);
                child.val = values[i];
                map.put(values[i], child);
            }
        }

        return graph;
    }

    private int[] strToIntArray(String str) {
        if(str.length() < 2) {
            return new int[0];
        }
        String[] intStrArray = str.substring(1, str.length() - 1).split(",");
        int[] res = new int[intStrArray.length];
        int index = 0;
        for(String intStr : intStrArray) {
            res[index++] = Integer.parseInt(intStr);
        }
        return res;
    }

    public static void main(String[] args) {
        GraphNode graph1 = new GraphNode(1);
        GraphNode graph2 = new GraphNode(2);
        GraphNode graph3 = new GraphNode(3);
        GraphNode graph4 = new GraphNode(4);

        graph1.neighbors.add(graph2);
        graph1.neighbors.add(graph3);

        graph2.neighbors.add(graph1);
        graph2.neighbors.add(graph4);

        graph3.neighbors.add(graph1);
        graph3.neighbors.add(graph4);

        graph4.neighbors.add(graph2);
        graph4.neighbors.add(graph3);

        SerializeGraph p = new SerializeGraph();
        String str = p.serialize(graph1);
        System.out.println(str);
        GraphNode graph = p.deserialzie(str);
        System.out.println(p.serialize(graph));

    }
}
