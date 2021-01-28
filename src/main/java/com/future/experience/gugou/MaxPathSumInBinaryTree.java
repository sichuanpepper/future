package com.future.experience.gugou;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class MaxPathSumInBinaryTree {
    /**
     1. Tree is valid.
     2. May contains negative.
     3. Path.length >= 1

      1
     / \
    2   3
   The max sum is 6
      1
     / \
    -2  3
   The max sum is 4
      -1
     / \
    2   3
   The max sum is 3

     Each node:
     - Contribute a max sum to parent, the max = max(0, (max(left, 0) or max(right, 0) + val)
     - Contribute the final max sum, max = max(left, 0) + max(right, 0) + val
     **/
    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxSum;
    }

    private int maxSum = Integer.MIN_VALUE;
    private int helper(TreeNode node) {
        if(node == null) return 0;
        int left = helper(node.left);
        int right = helper(node.right);
        maxSum = Math.max(maxSum, Math.max(left, 0) + Math.max(right, 0) + node.val);
        return Math.max(Math.max(Math.max(left, 0), Math.max(right, 0)) + node.val, 0);
    }
}
