package com.future.utils;

/**
 * Created by someone on 11/7/17.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public static TreeNode getBSTSample() {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(6);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(7);
        return head;
    }

    public static TreeNode getBTSample() {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.left.left = new TreeNode(8);
        head.left.right = new TreeNode(4);
        head.right = new TreeNode(7);
        head.right.left = new TreeNode(1);
        head.right.right = new TreeNode(2);
        return head;
    }

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return Integer.toString(this.val);
    }
}
