package com.future.experience.linying.eley;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * The goal of this problem is to list out in which order the leaves would "fall" off a tree.
 * A node only falls from the tree if it has no children. At each time step, several leaves will fall, giving an ordered list of sets of leaves that have fallen.
 *
 * https://www.programcreek.com/2014/07/leetcode-find-leaves-of-binary-tree-java/
 *
 * Thoughts:
 * - The problem is finding the index of each node in the result.
 * - The index actually depends on the height (or max depth of left and right) of the node.
 * - Bottom-up
 */
public class FallingLeaves {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private int helper(TreeNode root, List<List<Integer>> res) {
        if(root == null) {
            return -1;
        }

        int left = helper(root.left, res);
        int right = helper(root.right, res);
        int idx = Math.max(left, right) + 1;
        if(res.size() <= idx) {
            res.add(new ArrayList<>());
        }
        res.get(idx).add(root.val);
        return idx;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        FallingLeaves p = new FallingLeaves();
        p.findLeaves(root).forEach(System.out::println);
    }
}
