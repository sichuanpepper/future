package com.future.round2;

import com.future.utils.BTreePrinter;
import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/invert-binary-tree/description/
 *
 * Invert a binary tree.

      4
    /   \
   2     7
  / \   / \
 1   3 6   9
 to
     4
   /   \
  7     2
 / \   / \
 9   6 3   1
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

    public TreeNode invertTree2(TreeNode root) {
        if(root == null) return root;
        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(7);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(9);

        BTreePrinter.printNode(head);
        Problem226 p = new Problem226();
        BTreePrinter.printNode(p.invertTree(head));
        BTreePrinter.printNode(p.invertTree2(head));
    }
}
