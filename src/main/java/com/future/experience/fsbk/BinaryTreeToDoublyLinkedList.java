package com.future.experience.fsbk;

import com.future.utils.DoublyLinkedList;
import com.future.utils.TreeNode;

/**
 * https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
 */
public class BinaryTreeToDoublyLinkedList {
    public DoublyLinkedList convert(TreeNode root) {
        if(root == null) return null;
        helper(root);
        return header;
    }

    private DoublyLinkedList header = null;
    private DoublyLinkedList cur = null;

    private void helper(TreeNode root) {
        if(root == null) return;
        helper(root.left);
        DoublyLinkedList tmp = new DoublyLinkedList(root.val);
        if(header == null) header = tmp;
        if(cur != null) {
            tmp.pre = cur;
            cur.next = tmp;
        }
        cur = tmp;

        helper(root.right);
    }

    public static void main(String[] args) {
        BinaryTreeToDoublyLinkedList p = new BinaryTreeToDoublyLinkedList();
        DoublyLinkedList res = p.convert(TreeNode.getSample1());
        DoublyLinkedList pointer = res;
        DoublyLinkedList tail = null;
        while (pointer != null) {
            System.out.print(pointer.val + " ");
            if(pointer.next == null) tail = pointer;
            pointer = pointer.next;
        }
        System.out.println();
        while (tail != null) {
            System.out.print(tail.val + " ");
            tail = tail.pre;
        }
    }
}
