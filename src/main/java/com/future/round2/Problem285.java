package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/description/
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

 Note: If the given node has no in-order successor in the tree, return null.

 * Created by someone on 11/19/17.
 */
public class Problem285 {
    /**
     * Recursive solution:
     * 1. if p's value equals root's value, the result comes from right child.
     * 2. if p's value is greater than root's value, the result comes from right child.
     * 3. if p's value is smaller than root's value, the result comes from left child.
     * 4.
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //when to end recursive.
        if(root == null || p == null) return null;
        // if p's value equals or is greater than root's value.
        if(p.val >= root.val) return inorderSuccessor(root.right, p);
        // find the result in left child, the result must be either in right child or its parent,
        // so return root if null is returned from left child
        TreeNode leftRes = inorderSuccessor(root.left, p);
        return leftRes == null ? root : leftRes;
    }


}
