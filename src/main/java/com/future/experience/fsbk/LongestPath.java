package com.future.experience.fsbk;

import com.future.utils.TreeNode;

/**
 * Created by xingfeiy on 5/7/18.
 */
public class LongestPath {
    /**
     * Given a binary tree, find the longest path, the longest path may pass through root or may not.
     *
     * Analyze: First we have to understand how do we compute path, the path means the number of nodes or number of edges?
     *
     * Here, we consider the path is the number of edges.
     *
     * @param root
     * @return
     */

    public int longestPath(TreeNode root) {
        helper(root);
        return longest;
    }

    private int longest = 0;
    private int helper(TreeNode node) {
        if(node == null || (node.left == null && node.right == null)) return 0;
        int left = 0, right = 0;
        if(node.left != null) left = helper(node.left) + 1;
        if(node.right != null) right = helper(node.right) + 1;
        longest = Math.max(longest, (left + right));
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPath().longestPath(TreeNode.getSample1())); //4
        System.out.println(new LongestPath().longestPath(TreeNode.getSample2())); //0
        System.out.println(new LongestPath().longestPath(TreeNode.getSample3())); //6
    }
}
