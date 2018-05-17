package com.future.utils;

/**
 * Created by someone on 11/7/17.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

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


    /**
     *                5
     *              /  \
     *             3    6
     *            / \
     *           1   4
     *              /
     *             2
     * @return
     */
    public static TreeNode getSample1() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(2);
        return root;
    }

    /**
     *                5
     *              /  \
     *             3    6
     *            / \
     *           1   4
     *          /   /
     *         0   2
     *          \   \
     *          9    8
     * @return
     */
    public static TreeNode getSample3() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.right = new TreeNode(9);

        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(2);
        root.left.right.left.right = new TreeNode(8);
        return root;
    }

    public static TreeNode getSample2() {
        TreeNode root = new TreeNode(1);
        return root;
    }

    public static void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }



    @Override
    public String toString() {
        return Integer.toString(this.val);
    }
}
