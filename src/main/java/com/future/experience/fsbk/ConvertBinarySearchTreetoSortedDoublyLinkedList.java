package com.future.experience.fsbk;

import com.future.utils.TreeNode;

/**
 * https://www.lintcode.com/problem/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class ConvertBinarySearchTreetoSortedDoublyLinkedList {
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        // Write your code here.
        helper(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private TreeNode pre = null, head = null;
    private void helper(TreeNode root) {
        if(root == null) {
            return;
        }
        helper(root.left);
        if(pre != null) {
            pre.right = root;
            root.left = pre;
        }
        if(head == null) {
            head = root;
        }
        pre = root;
        helper(root.right);
    }
}
