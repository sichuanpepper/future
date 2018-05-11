package com.future.experience.facebook;

import com.future.utils.TreeNode;

/**
 * http://cslibrary.stanford.edu/109/TreeListRecursion.html
 *
 * Created by xingfeiy on 5/9/18.
 */
public class BST2DLL {
    public TreeNode treeToDLL(TreeNode root) {
        if(root == null) return null;
        helper(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private TreeNode pre = new TreeNode(0);
    private TreeNode head = null;
    public void helper(TreeNode node) {
        if(node == null) return;
        helper(node.left);
        node.left = pre;
        pre.right = node;
        if(head == null) head = node;
        pre = node;
        helper(node.right);
    }
}
