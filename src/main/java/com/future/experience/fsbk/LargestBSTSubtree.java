package com.future.experience.fsbk;

import com.future.utils.TreeNode;

/**
 * Problem:
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 *
 * Note:
 * A subtree must include all of its descendants.
 * Here's an example:
 *     10
 *     / \
 *    5  15
 *   / \   \
 *  1   8   7
 * The Largest BST Subtree in this case is the highlighted one.
 * The return value is the subtree's size, which is 3.
 * Hint:
 *
 * You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.
 * Follow up:
 * Can you figure out ways to solve it with O(n) time complexity?
 */
public class LargestBSTSubtree {
    /**
     * Just go through nodes one by one by DFS
     * For each node, if and only if
     *      - both left subtree and right subtree are BST and
     *      - node.val > max-value(left subtree) and node.val < min-val(right subtree)
     * So basically we care about the left and right subtree, we can use bottom-up solution,
     * each node contribute three status to its parent:
     *  - count of node if it's a bst, otherwise contribute -1.
     *  - max value
     *  - min value
     *
     * @param root
     * @return
     */
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) {
            return 0;
        }

        helper(root);
        return max;
    }

    private int max = 0;

    /**
     * int[]: 0 - count of bst, 1 - max val, 2 - min
     * @param node
     * @return
     */
    private int[] helper(TreeNode node) {
        if(node == null) {
            return new int[]{0, Integer.MIN_VALUE, Integer.MAX_VALUE};
        }
        int[] left = helper(node.left);
        int[] right = helper(node.right);

        int[] res;
        if(left[0] >= 0 && right[0] >= 0  && node.val > left[1] && node.val < right[2]) {
            res = new int[]{left[0] + right[0] + 1, Math.max(right[1], node.val), Math.min(left[2], node.val)};
        } else {
            res = new int[]{-1, node.val, node.val};
        }
        max = Math.max(max, res[0]);
        return res;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(7);
        System.out.println(new LargestBSTSubtree().largestBSTSubtree(root));
    }
}
