package com.future.experience.gugou;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class CountCompleteTreeNodes {
    /**
     * Solution 1:
     * - For any node with index i (1-based), the index of left child is 2 * i and right child is 2 * i + 1,
     * So the idea is try to do reverse pre-order of in-order traversal, visit right before left.
     * And then the problem is find the first leaf node in last level, which means we have to know the last level first.
     * Since it's complete tree, we can find the last level by the most left node.
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = 0;
        TreeNode node = root;
        while(node != null) {   //tc: O(h) where h is the height of tree
            leftHeight++;
            node = node.left;
        }
        return helper(root, 1, leftHeight, 1);
    }


    private int helper(TreeNode root, int index, int targetHeight, int curHeight) {
        if(root == null) return 0;
        if(curHeight == targetHeight) return index;
        int right = helper(root.right, index * 2 + 1, targetHeight, curHeight + 1);
        if(right > 0) return right;
        return helper(root.left, index * 2, targetHeight, curHeight + 1);
    }
}
