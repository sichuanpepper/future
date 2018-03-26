package com.future.round2;

import com.future.utils.TreeNode;

/**

 Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
 where largest means subtree with largest number of nodes in it.

 Note:
 A subtree must include all of its descendants.
 Here's an example:

          10
         / \
        5  15
       / \   \
      1   8   7
 The Largest BST Subtree in this case is the highlighted one (1, 5, 8).
 The return value is the subtree's size, which is 3.



 Hint:

 You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree,
 which will result in O(nlogn) time complexity.

 Follow up:
 Can you figure out ways to solve it with O(n) time complexity?

 * Created by xingfeiy on 3/20/18.
 */
public class Problem333 {
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return max;
    }

    private int preVal = Integer.MIN_VALUE;
    private int max = 0;
    private int curLength = 0;
    private void helper(TreeNode node) {
        if(node == null) return;
        helper(node.left);
        if(node.val > preVal) {
            max = Math.max(max, ++curLength);
        } else {
            curLength = 1;
        }
        preVal = node.val;
        helper(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Problem333 p = new Problem333();
        System.out.print(p.largestBSTSubtree(root));
    }
}
