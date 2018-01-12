package com.future.round2;

import com.future.utils.TreeNode;

/**
 https://leetcode.com/problems/sum-root-to-leaf-numbers/description/

 Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

 An example is the root-to-leaf path 1->2->3 which represents the number 123.

 Find the total sum of all root-to-leaf numbers.

 For example,

    1
   / \
  2   3
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.

 Return the sum = 12 + 13 = 25.
 * Created by xingfeiy on 1/10/18.
 */
public class Problem129 {
    public int sumNumbers(TreeNode root) {
        helper(root, 0);
        return res;
    }

    private int res = 0;
    private void helper(TreeNode node, int curVal) {
        if(node == null) return;
        if(node.left == null && node.right == null) {
            res += (curVal * 10 + node.val);
            return;
        }
        helper(node.left, curVal * 10 + node.val);
        helper(node.right, curVal * 10 + node.val);
    }
}
