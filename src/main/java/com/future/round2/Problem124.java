package com.future.round2;

import com.future.foundation.java.MapLearning;
import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 * Given a binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the
 parent-child connections. The path must contain at least one node and does not need to go through the root.

 For example:
 Given the below binary tree,

   1
  / \
 2   3
 Return 6.
 *
 * Created by someone on 12/1/17.
 */
public class Problem124 {
    /**
     * Analyze:
     * 1. Sequence of nodes, 2. at least one node, 3. doesn't need to go through the root.
     *
     * So for any subtree, the maximum path sum could be:
     *  - root self.
     *  - the maximum path sum of left child.
     *  - the maximum path sum of right child
     *  - root self + maximum_left_child
     *  - root self + maximum_right_child.
     *  - root self + maximum_left_child + maximum_right_child.
     * @param root
     * @return
     */

    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxValue;
    }

    private int maxValue = Integer.MIN_VALUE;
    private int helper(TreeNode node) {
        if(node == null) return 0;
        int left = helper(node.left);
        int right = helper(node.right);
        maxValue = Math.max(maxValue, Math.max(left, 0) + Math.max(right, 0) + node.val);
        return Math.max(Math.max(left, 0), Math.max(right, 0)) + node.val;
    }
}
