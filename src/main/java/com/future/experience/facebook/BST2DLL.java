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

    private TreeNode pre = null;
    private TreeNode head = null;
    public void helper(TreeNode node) {
        if(node == null) return;
        helper(node.left);
        if(head == null) head = node;
        if(pre != null) {
            pre.right = node;
            node.left = pre;
        }
        pre = node;
        helper(node.right);
    }

    public static void main(String[] args) {
        BST2DLL p = new BST2DLL();
        TreeNode root = p.treeToDLL(TreeNode.getBSTSample());
        TreeNode node = root;
        do{
            System.out.print(node.val + "->");
            node = node.left;
        } while (node != root);
    }
}
