package com.future.generic;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Based on the characteristics of BST above, we can come out:
 * - The problem can be divide into left and right sub-problems.
 * - The current value is bigger than the maximum value in its left sub-problem.
 * - The current value is smaller than the minimum value in its right sub-problem.
 * - Both left and right are BST.
 *
 * So for each sub-problem, we have to care about the max and min values.
 */
public class ValidateBinarySearchTree {

    /**
     * Solution 1: Straightforward solution, based on the characteristics of BST, do the post order, it's a bottom-up solution.
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        int[] res = helper(root);
        return res[2] == 1;
    }

    //returns int[]{max, min, isValid}
    private int[] helper(TreeNode root) {
        if(root == null) {
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 1};
        }

        int[] left = helper(root.left);
        int[] right = helper(root.right);
        if(left[2] == 1 && right[2] == 1 && left[0] < root.val && right[1] > root.val) {
            return new int[]{Math.max(right[0], root.val), Math.min(left[1], root.val), 1};
        }
        return new int[]{root.val, root.val, -1};
    }

    /**
     * Solution 2: Since the current value is greater than the maximum value from left, so we are able to narrow down the value range for the left sub-tree.
     * We do the same thing for all sub-tree, if all nodes have value between given range, then the tree is BST, it's a top-down solution.
     * @param root
     * @return
     */
    public boolean isValidBSTV2(TreeNode root) {
        return helper2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean helper2(TreeNode root, int min, int max) {
        if(root == null) {
            return true;
        }
        if(root.val <= min || root.val >= max) {
            return false;
        }
        return helper2(root.left, min, root.val) && helper2(root.right, root.val, max);
    }
}
