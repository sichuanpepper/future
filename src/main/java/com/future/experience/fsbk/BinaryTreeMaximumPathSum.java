package com.future.experience.fsbk;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any path.
 */
public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }
    private int max = Integer.MIN_VALUE;
    private int helper(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int sum = left + right + root.val;
        max = Math.max(sum, max);
        return Math.max(0, Math.max(left, right) + root.val);
    }
}
