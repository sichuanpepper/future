package com.future.experience.linying.eley;

import com.future.utils.TreeNode;

/**
 * Description
 *
 * Given: A binary tree where nodes have values.
 *
 * Problem: Test whether this tree is a binary search tree or not.
 *
 * Alternations and follow-ups:
 *
 * Restrict the candidate not to use any additional list
 * Restrict the candidate to only use recursion
 * Ask the candidate to think about a way to parallelize the algorithm
 *
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 */
public class IsBinaryTree {
    /**
     *
     * @param root
     * @return
     */
    public boolean isBinaryTree(TreeNode root) {
        return inorder(root);
    }

    private Integer pre = null;
    private boolean inorder(TreeNode root) {
        if(root == null) {
            return true;
        }
        if(!inorder(root.left)) {
            return false;
        }
        if(pre != null && root.val <= pre) {
            return false;
        }
        pre = root.val;
        return inorder(root.right);
    }
}
