package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
 Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 * Created by xingfeiy on 3/20/18.
 */
public class Problem111 {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        int min = Integer.MAX_VALUE;
        if(root.left != null) {
            min = minDepth(root.left);
        }
        if(root.right != null) {
            min = Math.min(min, minDepth(root.right));
        }

        return min + 1;
    }


}
