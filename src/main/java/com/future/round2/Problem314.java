package com.future.round2;

import com.future.utils.BTreePrinter;
import com.future.utils.DisplayUtils;
import com.future.utils.Interval;
import com.future.utils.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to right.

 Examples:
 Given binary tree [3,9,20,null,null,15,7],

      3
     / \
    9  20
      /  \
     15   7


 return its vertical order traversal as:

 [
 [9],
 [3,15],
 [20],
 [7]
 ]


 Given binary tree [3,9,20,4,5,2,7],

             _3_
            /   \
           9    20
          / \   / \
         4   5 2   7


 return its vertical order traversal as:

 [
 [4],
 [9],
 [3,5,2],
 [20],
 [7]
 ]
 * Created by someone on 11/15/17.
 *
 */
public class Problem314 {
    /**
     * Analyze:
     * 1. We can retrieve the tree by BFS, and use a map to store the position of each node in each level.
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        helper(root, 0, res);
        return res;
    }

    private int mostLeft = 0;
    private void helper(TreeNode node, int pos, List<List<Integer>> res) {
        if(node == null) return;
        helper(node.left, pos - 1, res);
        mostLeft = Math.min(pos, mostLeft);
        int insertPos = pos - mostLeft;
        if(insertPos >= res.size()) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(node.val);
            res.add(tmp);
        } else {
            res.get(insertPos).add(node.val);
        }

        helper(node.right, pos + 1, res);
    }

    public static void main(String[] args) {
        Problem314 p = new Problem314();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        BTreePrinter.printNode(root);
        DisplayUtils.printLists(p.verticalOrder(root));

        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
//        root.left.right.right = new TreeNode(6);
//        root.left.right.right.right = new TreeNode(1);

        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(7);

        BTreePrinter.printNode(root);
        DisplayUtils.printLists(p.verticalOrder(root));
    }
}
