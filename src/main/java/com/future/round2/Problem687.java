package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/longest-univalue-path/description/
 *
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root.

 Note: The length of path between two nodes is represented by the number of edges between them.

 [TAGS] Binary Tree, Recursive
 *
 * Created by xingfeiy on 12/10/17.
 */
public class Problem687 {
    /**
     * Analyze:
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        return max;
    }

    private int max = 0;
    private int helper(TreeNode node) {
        if(node == null) return 0;
        int left = helper(node.left);
        int right = helper(node.right);
        int res = 0;

        int finalLeft = 0, finalRight = 0;
        if(node.left != null && node.val == node.left.val) {
            res = res + left + 1;
            finalLeft = left + 1;
        }
        if(node.right != null && node.val == node.right.val) {
            res = res + right + 1;
            finalRight = right + 1;
        }
        max = Math.max(max, res); //the max of current node.
        return Math.max(finalLeft, finalRight);
    }
}
