package com.future.round2;

import apple.laf.JRSUIUtils;
import com.future.utils.BTreePrinter;
import com.future.utils.DisplayUtils;
import com.future.utils.TreeNode;

/**
 Given a binary tree where all the right nodes are either leaf nodes with a sibling
 (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original
 right nodes turned into left leaf nodes. Return the new root.

 For example:
 Given a binary tree {1,2,3,4,5},
     1
    / \
   2  3
  / \
 4  5

 return the root of the binary tree [4,5,2,#,#,3,1].
     4
    / \
   5  2
     / \
    3  1

 * Created by xingfeiy on 1/2/18.
 */
public class Problem156 {
    /**
     * Analyze:
     * since all the right nodes are either leaf nodes with a sibling or empty, which means there are at most two nodes in each layer.
     *  - if node is null, return null
     *  - if node without children, return node.
     *  - if node has left child only, tmp = node, node = node.left, node.right = tmp
     *  - if node has left and right children, tmp = node, tmpRight = node.right, node = node.left, node.right = tmp, node.left = tmpRight.
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right = new TreeNode(3);
        BTreePrinter.printNode(head);
        System.out.println("======================================");
        Problem156 p = new Problem156();
        BTreePrinter.printNode(p.upsideDownBinaryTree(head));
    }
}
