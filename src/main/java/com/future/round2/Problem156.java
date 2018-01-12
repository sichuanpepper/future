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
     * It take a while to understand this problem
     *  - If a node has right child, its left child must be available.
     *  - Since the right child must be leaf or empty, there are at most two nodes in each layer.
     *
     * So it's easy to come out the recursive solution, process left subtree, then itself, and nothing need to do for right subtree.
     * It's could be post-order or in-order traversal, and then do your business.
     * Let's use a simple example:
     *     2
     *    / \
     *   4  5
     * After the flipping, we will get
     *     4
     *    / \
     *   5  2
     * The business here is:
     * node.left.left = node.right
     * node.left.right = node
     * node.left = null && node.right = null (don't forget it)
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
