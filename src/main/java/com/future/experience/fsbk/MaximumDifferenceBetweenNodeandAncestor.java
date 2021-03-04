package com.future.experience.fsbk;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
 *
 * Analyze:
 * The straightforward way is bottom-up, for any node, both left sub-tree and right sub-tree contributes its max, min number.
 * And then we are able to compute the maximum difference for current node.
 *
 * For th bottom-up solution, each node need to compare with 4 numbers, 2 from left and 2 from right.
 * But if we use top-down, same thing, we just need pass the max and min number to its children.
 * Notices: the difference between path from ancestor and the path from any other node,
 * we can use top-down here because the path is from ancestor.
 */
public class MaximumDifferenceBetweenNodeandAncestor {
    public int maxAncestorDiff(TreeNode root) {
        helper(root);
        return max;
    }

    private int max = Integer.MIN_VALUE;
    private int[] helper(TreeNode node) {
        if(node == null) {
            return null;
        }

        int[] left = helper(node.left);
        int[] right = helper(node.right);
        if(left == null && right == null) {
            return new int[]{node.val, node.val};
        } else if(left == null) {
            int curMax = Math.max(Math.abs(node.val - right[0]), Math.abs(node.val - right[1]));
            max = Math.max(max, curMax);
            return new int[]{Math.min(node.val, right[0]), Math.max(node.val, right[1])};
        } else if(right == null) {
            int curMax = Math.max(Math.abs(node.val - left[0]), Math.abs(node.val - left[1]));
            max = Math.max(max, curMax);
            return new int[]{Math.min(node.val, left[0]), Math.max(node.val, left[1])};
        } else {
            int curMax = Math.max(Math.abs(node.val - Math.min(left[0], right[0])),
                    Math.abs(node.val - Math.max(left[1], right[1])));
            max = Math.max(max, curMax);
            return new int[]{Math.min(Math.min(left[0], right[0]), node.val),
                    Math.max(Math.max(left[1], right[1]), node.val)};
        }
    }

    /**
     * We record the min and max for each path from root to leaf
     * @param node
     * @param min
     * @param max
     * @return
     */
    private int topDown(TreeNode node, int min, int max) {
        if(node == null) {
            return max - min;
        }
        min = Math.min(node.val, min);
        max = Math.max(node.val, max);
        return Math.max(topDown(node.left, min, max), topDown(node.right, min, max));
    }
}
