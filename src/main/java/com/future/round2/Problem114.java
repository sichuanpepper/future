package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
 *
 * Created by someone on 12/1/17.
 */
public class Problem114 {
    /**
     * Analyze:
     * The straightforward way is flatten left and flatten right first and then move left to right, and then move the right
     * to the tail of left.
     * @param root
     */
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode tmpRight = root.right;
        root.right = root.left == null ? root.right : root.left;
        root.left = null;
        TreeNode tmp = root;
        while (tmp.right != null) tmp = tmp.right;
        tmp.right = tmpRight;
    }

    /**
     * Analyze:
     * In the above straightforward solution, the time complexity is O(nh) where h is the height of tree, because we have to
     * find the tail of left subtree.
     * In the above solution, the basic idea is flatten left and right side, that means we finished all node in left side
     * and return back to process right side, when we start to process the right side, at this moment, we have the root of left subtree only.
     * The root of left subtree is useless for us, we need the tail of subtree.
     *
     * But, we need the root of right subtree, we use that to connect with the tail node of left subtree.
     * We traversal the right first, that means we will start from the right most node, we can use a variable to store it.
     *
     * for example
     *    1
     *   / \
     *  2   3
     *  After we flattened this tree, the tree looks like this:
     *   1
     *    \
     *     2
     *      \
     *       3
     *
     * @param root
     */
    private TreeNode preNode = null;
    public void flatten2(TreeNode root) {
        if(root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = preNode;
        root.left = null;
        preNode = root;
    }

}
