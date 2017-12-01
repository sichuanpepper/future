package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 *
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 How would you optimize the kthSmallest routine?

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.

 * Created by xingfeiy on 11/30/17.
 */
public class Problem230 {
    /**
     * Analyze:
     * For BST, the in-order traversal comes out the ascending order.
     * So we just need do our business in the middle place, here our business is check if it's kth element.
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return value;
    }


    private int curCount = 0;
    private int value = Integer.MIN_VALUE;
    private void helper(TreeNode node, int k) {
        if(node == null) return;
        helper(node.left, k);
        curCount++;
        if(curCount == k) {
            value = node.val;
            return;
        }
        helper(node.right, k);
    }


    /**
     * Analyze:
     * It's binary search tree, we can do binary search for sure, calculate how many nodes in left side,
     * if the number of node in left child is greater than k, find k -1 in left subtree.
     * otherwise, find k - left_nodes - 1 in right subtree.
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        int left = count(root.left);
        if(left >= k) {
            return kthSmallest2(root.left, k);
        } else if(left + 1 < k){
            return kthSmallest2(root.right, k - left - 1);
        }
        return root.val;
    }

    private int count(TreeNode node) {
        if(node == null) return 0;
        return count(node.left) + count(node.right) + 1;
    }

}
