package com.future.experience.fsbk;

import com.future.utils.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree543 {
    /**
     * Any path can be written as two arrows (in different directions) from some node, where an arrow is a path that starts at some node and only travels down to child nodes.
     *
     * If we knew the maximum length arrows L, R for each child, then the best path touches L + R + 1 nodes.
     *
     * Algorithm
     *
     * Let's calculate the depth of a node in the usual way: max(depth of node.left, depth of node.right) + 1.
     * While we do, a path "through" this node uses 1 + (depth of node.left) + (depth of node.right) nodes.
     * Let's search each node and remember the highest number of nodes used in some path. The desired length is 1 minus this number.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return res;
    }

    private int res = 0;
    private int helper(TreeNode root) {
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }
}
