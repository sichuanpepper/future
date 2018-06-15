package com.future.experience.fsbk;

import com.future.utils.TreeNode;

/**
 * https://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
 *
 * Given two Binary Trees, write a function that returns true if two trees are mirror of each other, else false.
 * Created by xingfeiy on 5/1/18.
 */
public class MirrorBinaryTree {
    public boolean isMirrorBT(TreeNode tree1, TreeNode tree2) {
        if(tree1 == null || tree2 == null) return tree1 == tree2;
        if(tree1.val != tree2.val) return false;
        return isMirrorBT(tree1.left, tree2.right) && isMirrorBT(tree1.right, tree2.left);
    }

    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(3);
        tree1.right = new TreeNode(2);
        tree1.right.left = new TreeNode(5);
        tree1.right.right = new TreeNode(4);

        TreeNode tree2 = new TreeNode(1);
        tree2.left = new TreeNode(2);
        tree2.left.left = new TreeNode(4);
        tree2.left.right = new TreeNode(5);
        tree2.right = new TreeNode(3);

        MirrorBinaryTree p = new MirrorBinaryTree();
        System.out.println(p.isMirrorBT(null, null)); //true
        System.out.println(p.isMirrorBT(tree1, null)); //false
        System.out.println(p.isMirrorBT(null, tree2)); //false
        System.out.println(p.isMirrorBT(tree1, tree2)); //true

    }
}
