package com.future.foundation.datastructure;

import com.future.utils.TreeNode;

/**
 * Created by xingfeiy on 6/18/18.
 */
public class BSTOperations {
    public static TreeNode search(TreeNode root, int val) {
        if(root == null || root.val == val) return root;
        return root.val < val ? search(root.right, val) : search(root.left, val);
    }

    /**
     * For any given val, it always can be insert to leaf.
     * @param root
     * @param val
     */
    public static TreeNode insert(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(root.val > val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    /**
     * Delete:
     * - find the node
     *  - if left child is null, return right child
     *  - if right child is null, return left child.
     *  - find the minimal val in right sub tree and assign this minimal val to current node, then delete minimal node in right sub tree.
     * @param root
     * @param val
     * @return
     */
    public static TreeNode delete(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val < val) {
            root.right = delete(root.right, val);
        } else if(root.val > val) {
            root.left = delete(root.left, val);
        } else {
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;
            //find minimal val in right sub tree.
            TreeNode minNode = root.right;
            while (minNode.left != null) minNode = minNode.left;
            root.val = minNode.val;
            root.right = delete(root.right, root.val);
        }
        return root;
    }
}
