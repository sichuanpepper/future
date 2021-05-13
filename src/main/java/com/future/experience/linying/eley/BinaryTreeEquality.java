package com.future.experience.linying.eley;

import com.future.utils.TreeNode;

/**
 * Question Description:
 * Implement a function which takes two binary trees as input and returns true if they are equal and false otherwise.
 *
 * https://leetcode.com/problems/same-tree/
 */
public class BinaryTreeEquality {
    public boolean equalsTo(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null) {
            return root1 == root2;
        }
        if(root1.val != root2.val) {
            return false;
        }
        return equalsTo(root1.left, root2.left) && equalsTo(root1.right, root2.right);
    }
}
