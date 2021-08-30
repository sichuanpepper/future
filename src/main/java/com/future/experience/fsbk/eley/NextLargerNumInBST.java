package com.future.experience.fsbk.eley;

import com.future.utils.TreeNode;

/**
 * Given a binary search tree and a number, find the next larger number in the tree
 */
public class NextLargerNumInBST {
    public int nextLarger(TreeNode root, int num) {
        helper(root, num);
        return candidate;
    }

    private int candidate = Integer.MAX_VALUE;
    private void helper(TreeNode root, int num) {
        if(root == null) {
            return;
        }
        if(root.val <= num) {
            helper(root.right, num);
        } else {
            candidate = root.val;
            helper(root.left, num);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        NextLargerNumInBST p = new NextLargerNumInBST();
        System.out.println(p.nextLarger(root, 7));
        System.out.println(p.nextLarger(root, 5));
        System.out.println(p.nextLarger(root, 3));
        System.out.println(p.nextLarger(root, 1));
    }
}
