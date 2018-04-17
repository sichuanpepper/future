package com.future.round2;

import com.future.utils.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/description/
 *
 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

 * Created by xingfeiy on 4/9/18.
 */
public class Problem173 {
    private Stack<TreeNode> stack = new Stack<>();

    public Problem173(TreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if(stack.isEmpty()) return Integer.MIN_VALUE;
        TreeNode node = stack.pop();
        int value = node.val;
        node = node.right;
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
        return value;
    }
}
