package com.future.experience.facebook;

import com.future.utils.TreeNode;

/**
 * Created by xingfeiy on 5/21/18.
 */
public class CompleteTree {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return true;
        return helper(root, 0);
    }

    private int preDepth = Integer.MIN_VALUE;
    private boolean helper(TreeNode node, int curDepth) {
        if(node == null) {
            if(preDepth != Integer.MIN_VALUE && (curDepth < preDepth || curDepth - preDepth > 1)) return false;
            preDepth = curDepth;
            return true;
        }
        if(!helper(node.right, curDepth + 1)) return false;
        return helper(node.left, curDepth + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        CompleteTree p = new CompleteTree();
        System.out.println(p.isCompleteTree(root));
    }
}
