package com.future.round2;

import com.future.utils.TreeNode;

/**
 * [tag] Facebook
 * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 */
public class Problem1123 {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root == null) return root;
        helper(root, 0);
        return lca;
    }

    private int maxDepth = 0;
    private TreeNode lca = null;
    private int helper(TreeNode root, int curDepth) {
        if(root == null) return curDepth;
        int left = helper(root.left, curDepth + 1);
        int right = helper(root.right, curDepth + 1);
        if(left == right && left >= maxDepth) lca = root;
        maxDepth = Math.max(maxDepth, Math.max(left, right));
        return Math.max(left, right);
    }
}
