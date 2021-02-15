package com.future.experience.linying;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 */
public class SecondMinimumNodeInaBinaryTree {
    /**
     * The root is always minimal value in the tree.
     * The root equals either left or right, or equals both.
     * If root equals left, try to find second minimal number in left sub-tree.
     *  - If found, compare it to the minimal value from right sub-tree
     *  - Not found, check the minimal value from right sub-tree.
     * Same thing for right
     *
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null || root.left == null || root.right == null) {
            return -1;
        }

        int left = root.left.val, right = root.right.val;
        if(left == root.val) {
            left = findSecondMinimumValue(root.left);
        }

        if(right == root.val) {
            right = findSecondMinimumValue(root.right);
        }
        if(left != -1 && right != -1) {
            return Math.min(left, right);
        } else if(left == -1) {
            return right;
        } else {
            return left;
        }
    }
}
