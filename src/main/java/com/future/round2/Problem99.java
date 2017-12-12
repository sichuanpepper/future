package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/description/
 *
 *
 Two elements of a binary search tree (BST) are swapped by mistake.

 Recover the tree without changing its structure.

 Note:
 A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

 * Created by xingfeiy on 12/10/17.
 */
public class Problem99 {
    /**
     * Analyze:
     * The in-order traversal will produce a sorted array, and there are two elements are swapped by mistake,
     * that means there are two elements don't satisfy the ascending order.
     * example: 1, 2, 3, 4, 5, 6
     * Mistake:
     * 6, 2, 3, 4, 5, 1
     * - Found 6 and 2 don't satisfy, but we don't know which one is wrong or both are wrong, so we record both position first.
     *   [0, 1], if this is the only pair which violated the order, then swap it, otherwise, means there's one more pair violated,
     *   but as problem said, there are only two elements swapped by mistake, so only possible is the first element in first pair and second
     *   element in second pair.
     *   1, 3, 2, 4, 5, 6  swap position(1, 2)
     *
     * @param root
     */
    private TreeNode[] swapped = new TreeNode[2];
    private TreeNode preNode = null;
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        helper(root);
        int tmp = swapped[0].val;
        swapped[0].val = swapped[1].val;
        swapped[1].val = tmp;
    }

    private void helper(TreeNode node) {
        if(node == null) return;
        helper(node.left);
        if(preNode != null && node.val <= preNode.val) {
            if(swapped[0] == null) {
                swapped[0] = preNode;
                swapped[1] = node;
            } else {
                swapped[1] = node;
            }
        }
        preNode = node;
        helper(node.right);
    }
}
