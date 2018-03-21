package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/sum-of-left-leaves/description/
 *
 Find the sum of all left leaves in a given binary tree.

 Example:

        3
       / \
      9  20
        /  \
       15   7

 There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

 * Created by xingfeiy on 3/20/18.
 */
public class Problem404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int res = 0;
        if(root.left != null) {
            res = (root.left.left == null && root.left.right == null) ? root.left.val : sumOfLeftLeaves(root.left);
        }
        return res + sumOfLeftLeaves(root.right);
    }
}
