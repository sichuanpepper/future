package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/print-binary-tree/
 */
public class Problem655 {
    public List<List<String>> printTree(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        int height = getHeight(root);
        List<List<String>> res = new ArrayList<>(height);
        int width = 0;
        for(int i = 0; i < height; i++) {
            width += Math.pow(2, i);
        }

        String[] row = new String[width];
        Arrays.fill(row, "");
        for(int i = 0; i< height; i++) {
            res.add(new ArrayList<>(Arrays.asList(row)));
        }

        helper(root, 0, res, 0, width - 1);

        return res;
    }

    private void helper(TreeNode node, int row, List<List<String>> res, int start, int end) {
        if(node == null || row >= res.size()) {
            return;
        }

        int mid = start + (end - start) / 2;
        res.get(row).set(mid, Integer.toString(node.val));
        helper(node.left, row + 1, res, start, mid - 1);
        helper(node.right, row + 1, res, mid + 1, end);
    }

    private int getHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
