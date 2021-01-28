package com.future.experience.fsbk;

import com.future.utils.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the vertical order traversal of its nodes values.
 *
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 *
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes,
 * we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 *
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 *
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 *
 *
 * Note:
 *
 * The tree will have between 1 and 1000 nodes.
 * Each node's value will be between 0 and 1000.
 *
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 */
public class VerticalOrderTraversalofaBinaryTree987 {
//    public List<List<Integer>> verticalTraversal(TreeNode root) {
//        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
//        dfs(root, 0, 0, map);
//        List<List<Integer>> list = new ArrayList<>();
//        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
//            list.add(new ArrayList<>());
//            for (PriorityQueue<Integer> nodes : ys.values()) {
//                while (!nodes.isEmpty()) {
//                    list.get(list.size() - 1).add(nodes.poll());
//                }
//            }
//        }
//        return list;
//    }
//    private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
//        if (root == null) {
//            return;
//        }
//        if (!map.containsKey(x)) {
//            map.put(x, new TreeMap<>());
//        }
//        if (!map.get(x).containsKey(y)) {
//            map.get(x).put(y, new PriorityQueue<>());
//        }
//        map.get(x).get(y).offer(root.val);
//        dfs(root.left, x - 1, y + 1, map);
//        dfs(root.right, x + 1, y + 1, map);
//    }

    private class LocNode {
        public int val;
        public int x;
        public int y;
        public LocNode(int x, int y, int val) {
            this.val = val;
            this.x = x;
            this.y = y;
        }

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<Integer, List<LocNode>> map = new TreeMap<>();
        helper(root, 0, 0, map);
        for(Map.Entry<Integer, List<LocNode>> entry : map.entrySet()) {
            List<LocNode> locNodes = entry.getValue();
            Collections.sort(locNodes, (o1, o2) -> {
                if(o1.x != o2.x) {
                    return Integer.compare(o1.x, o2.x);
                } else if(o1.y != o2.y) {
                    return Integer.compare(o2.y, o1.y);
                } else {
                    return Integer.compare(o1.val, o2.val);
                }
            });
            List<Integer> tmpVal = new ArrayList<>();
            for(LocNode locNode : locNodes) tmpVal.add(locNode.val);
            res.add(tmpVal);
        }
        return res;
    }

    private void helper(TreeNode root, int x, int y, Map<Integer, List<LocNode>> map) {
        if(root == null) return;
        helper(root.left, x - 1, y - 1, map);
        List<LocNode> list = map.getOrDefault(x, new ArrayList<>());
        list.add(new LocNode(x, y, root.val));
        map.put(x, list);
        helper(root.right, x + 1, y - 1, map);
    }
}
