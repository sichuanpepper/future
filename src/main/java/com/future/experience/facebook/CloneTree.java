package com.future.experience.facebook;

import com.future.utils.TreeNode;

/**
 * Created by xingfeiy on 5/3/18.
 */
public class CloneTree {
    public TreeNode cloneTree(TreeNode root) {
        if(root == null) return null;
        TreeNode head = new TreeNode(root.val);
        head.left = cloneTree(root.left);
        head.right = cloneTree(root.right);
        return head;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.getBSTSample();
        System.out.println("Original");
        TreeNode.inOrder(root);
        CloneTree clone = new CloneTree();
        System.out.println("Clone");
        TreeNode.inOrder(clone.cloneTree(root));
    }
}
