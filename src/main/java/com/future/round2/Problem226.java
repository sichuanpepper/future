package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/invert-binary-tree/description/
 *
 * Created by someone on 11/19/17.
 */
public class Problem226 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        TreeNode leftTmp = root.left;
        TreeNode rightTmp = root.right;
        root.left = invertTree(rightTmp);
        root.right = invertTree(leftTmp);
        return root;
    }
}
