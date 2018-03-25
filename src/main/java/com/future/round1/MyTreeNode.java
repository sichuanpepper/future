package com.future.round1;

/**
 * Created by someone on 8/29/17.
 */
public class MyTreeNode {
    int val;
    MyTreeNode left;
    MyTreeNode right;

    MyTreeNode(int x) {
        val = x;
    }

    /**
     *                      6
     *                 3        8
     *              1     4   7    9
     * @return
     */
    public static MyTreeNode getSample() {
        MyTreeNode root = new MyTreeNode(6);
        root.left = new MyTreeNode(3);
        root.left.left = new MyTreeNode(1);
        root.left.right = new MyTreeNode(4);
        root.right = new MyTreeNode(8);
        root.right.left = new MyTreeNode(7);
        root.right.right = new MyTreeNode(9);
        return root;
    }
}
