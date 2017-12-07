package com.future.round2;

import com.future.utils.Interval;
import com.future.utils.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/
 * Created by someone on 11/15/17.
 *
 */
public class Problem314 {
    /**
     * Analyze:
     * 1. We can retrieve the tree by BFS, and use a map to store the position of each node in each level.
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        helper(root, 0, res);
        return res;
    }

    private void helper(TreeNode node, int pos, List<List<Integer>> res) {
        if(node == null) return;
        if(pos < 0) {
            List tmp = new ArrayList<>();
            tmp.add(node.val);
            res.add(0, tmp);
        } else if(pos >= res.size()) {
            List tmp = new ArrayList<>();
            tmp.add(node.val);
            res.add(tmp);
        } else {
            res.get(pos).add(node.val);
//            ArrayList<Integer> tmp = (ArrayList<Integer>) res.get(pos);
//            tmp.add(node.val);
        }

        if(node.left != null) {
            helper(node.left, pos++ - 1, res);
        }
        helper(node.right, pos + 1, res);
    }

    public static void main(String[] args) {
        Problem314 p = new Problem314();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        p.verticalOrder(root);
    }
}
