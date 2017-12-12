package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 * - facebook
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.

 * Created by someone on 11/21/17.
 */
public class Problem98 {
    private long curVal = Long.MIN_VALUE;

    /**
     * The in-order of a BST is ascending array.
     * So we can just in-order traversal this tree, and use a variable to keep current value,
     * if get a node which its value is less than current value, return false;
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        if(!isValidBST(root.left)) return false;
        if(root.val <= curVal) return false;
        curVal = root.val;
        return isValidBST(root.right);
    }

    /**
     * Even though both the left subtree and right subtree are binary search tree, we also can't not say the whole tree must be binary tree.
     * @param root
     * @return
     */
    public boolean FAILD(TreeNode root) {
        if(root == null) return true;
        if(root.left != null && root.val <= root.left.val) return false;
        if(root.right != null && root.val >= root.right.val) return false;
        return isValidBST(root.left) && isValidBST(root.right);
    }
}
