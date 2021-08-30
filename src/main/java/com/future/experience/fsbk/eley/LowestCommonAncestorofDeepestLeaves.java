package com.future.experience.fsbk.eley;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 *
 * Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
 *
 * Recall that:
 *
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
 * The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
 * Note: This question is the same as 865: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 *
 * Thoughts:
 * - The first thing is we need to find the deepest leaves, the straightforward way is start from root and pass the incremented depth to children.
 * - Then we can bottom-up and check the left depth and right depth, if the left depth == right depth and is the deepest so far, then the current
 *   node is the result so far, we keep bottom-up and updating the result util reached the root.
 */
public class LowestCommonAncestorofDeepestLeaves {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root == null) {
            return null;
        }

        helper(root, 0);
        return lca;
    }

    private int deepest = -1;
    private TreeNode lca = null;
    /**
     * We have to pass an incremented depth in top-down way, so we have curDepth as parameter.
     * @param root
     * @param curDepth
     * @return
     */
    private int helper(TreeNode root, int curDepth) {
        if(root == null) {
            return curDepth;
        }

        int leftDepth = helper(root.left, curDepth + 1);
        int rightDepth = helper(root.right, curDepth + 1);
        deepest = Math.max(Math.max(leftDepth, rightDepth), deepest);
        if(leftDepth == rightDepth && leftDepth > deepest) {
            deepest = leftDepth;
            lca = root;
        }
        return Math.max(leftDepth, rightDepth);
    }
}
