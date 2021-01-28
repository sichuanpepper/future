package com.future.experience.fsbk;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }

    private void helper(TreeNode root, int level, List<Integer> res) {
        if(root == null) return;
        if(res.size() < level + 1) res.add(root.val);
        helper(root.right, level + 1, res);
        helper(root.left, level + 1, res);
    }
}
