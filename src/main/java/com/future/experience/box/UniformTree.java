package com.future.experience.box;

import com.future.utils.TreeNode;

/**
 * Created by xingfeiy on 8/13/18.
 */
public class UniformTree {
    public boolean isUniformTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return true;
        if(isUniformTree(root.left) && isUniformTree(root.right)) {
            int left = root.left == null ? root.val : root.left.val;
            int right = root.right == null ? root.val : root.right.val;
            return (root.val == left) && (root.val == right);
        }
        return false;
    }

    public int countUniformSubtrees(TreeNode root) {
        helper(root);
        return count;
    }

    private int count = 0;
    private boolean helper(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) {
            count++;
            return true;
        }
        if(helper(root.left) && helper(root.right)) {
            int left = root.left == null ? root.val : root.left.val;
            int right = root.right == null ? root.val : root.right.val;
            if((left == root.val) && (right == root.val)) {
                count++;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = null;
        UniformTree u = new UniformTree();
        System.out.println(u.isUniformTree(root)); //true
        root = new TreeNode(1);
        System.out.println(u.isUniformTree(root)); //true
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        System.out.println(u.isUniformTree(root)); //true
        root.right = new TreeNode(1);
        System.out.println(u.isUniformTree(root)); //true
        root.right.left = new TreeNode(2);
        System.out.println(u.isUniformTree(root)); //false
        root.right.left.left = new TreeNode(1);
        System.out.println(u.isUniformTree(root)); //false
        System.out.println(u.countUniformSubtrees(root));
    }
}
