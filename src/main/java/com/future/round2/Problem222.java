package com.future.round2;

import com.future.utils.TreeNode;

/**
 * Created by xingfeiy on 3/13/18.
 */
public class Problem222 {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int height = height(root);
        int rightHeight = height(root.right);
        if(rightHeight + 1 == height) {
            // 1 << 2 + 1 is 1 << 3, not (1 << 2) + 1
            return (1 << (height - 1)) + countNodes(root.right);
        } else {
            return (1 << (height - 2)) + countNodes(root.left);
        }
    }

    private int height(TreeNode node) {
        return node == null ? 0 : 1 + height(node.left);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        Problem222 p = new Problem222();
        System.out.println(p.countNodes(head));
    }
}
