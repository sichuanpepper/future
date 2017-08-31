package com.future.round1;

/**
 * Created by xingfeiy on 8/29/17.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    /**
     *                      6
     *                 3        8
     *              1     4   7    9
     * @return
     */
    public static TreeNode getSample() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        return root;
    }
}
