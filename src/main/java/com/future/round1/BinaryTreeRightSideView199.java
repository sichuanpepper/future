package com.future.round1;

import java.util.*;

/**
 * Created by xingfeiy on 6/21/17.
 */
public class BinaryTreeRightSideView199 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        //return solution1(root);
        //return solution2(root);
        return solution3(root);
    }

    private List<Integer> solution1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    res.add(node.val);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return res;
    }

    private List<Integer> solution2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap<>();
        solution2(root, map, 1);

        int level = 1;
        for (int i = 0; i < map.size(); i++) {
            res.add(map.get(level++));
        }
        return res;
    }

    private void solution2(TreeNode node, Map<Integer, Integer> map, int level) {
        if (node == null) {
            return;
        }

        map.put(level, node.val);
        solution2(node.left, map, level + 1);
        solution2(node.right, map, level + 1);
    }

    private List<Integer> solution3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        solution3(root, res, 1);
        return res;
    }

    private int maxLevel = 0;

    private void solution3(TreeNode node, List<Integer> res, int level) {
        if (node == null) {
            return;
        }

        if (level > maxLevel) {
            res.add(node.val);
            maxLevel = level;
        }

        solution3(node.right, res, level + 1);
        solution3(node.left, res, level + 1);
    }


}

