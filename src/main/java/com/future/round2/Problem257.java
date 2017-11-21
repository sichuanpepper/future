package com.future.round2;

import com.future.utils.DisplayUtils;
import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 *  Given a binary tree, return all root-to-leaf paths.

 For example, given the following binary tree:

   1
 /   \
 2     3
 \
 5
 All root-to-leaf paths are:

 ["1->2->5", "1->3"]
 *
 * Created by someone on 11/14/17.
 */
public class Problem257 {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        helper(root, res, "");
        return res;
    }

    private static void helper(TreeNode node, List<String> res, String curStr) {
        if(node == null) return;
        curStr = curStr == "" ? Integer.toString(node.val) : curStr + "->" + node.val;
        if(node.left == null && node.right == null) {
            res.add(curStr);
            return;
        }
        helper(node.left, res, curStr);
        helper(node.right, res, curStr);
    }

    public static void main(String[] args) {
        //test cases
        // 1. root is null
        DisplayUtils.printList(binaryTreePaths(null));
        //2. only one node
        TreeNode root1 = new TreeNode(1);
        DisplayUtils.printList(binaryTreePaths(root1));
        //3. others
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        DisplayUtils.printList(binaryTreePaths(root2));

        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(3);
        DisplayUtils.printList(binaryTreePaths(root3));
    }
}
