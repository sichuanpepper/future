package com.future.experience.linying;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 Example:
 Given binary tree

        1
       / \
      2   3
     / \
    4   5
 Returns [4, 5, 3], [2], [1].

 Explanation:

 Removing the leaves [4, 5, 3] would result in this tree:

 1
 /
 2

 Now removing the leaf [2] would result in this tree:

 1
 Now removing the leaf [1] would result in the empty tree:

 []
 Returns [4, 5, 3], [2], [1].

 * Created by xingfeiy on 6/17/18.
 */
public class FindLeavesofBinaryTreeProblem366 {
    /**
     * Analyze:
     * The problem is, create a list with size height of tree, and then find the insert position for each node.
     * How to find the right position?
     * - We remove leafs each time, how many times we need to remove all sub nodes of current node? the max height of (left or right)
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        helper(root, res);
        return res;
    }

    private int helper(TreeNode node, List<List<Integer>> res) {
        if(node == null) return -1;
        int left = helper(node.left, res);
        int right = helper(node.right, res);
        int insertPos = Math.max(left, right) + 1;
        if(insertPos >= res.size()) res.add(new ArrayList<>());
        res.get(insertPos).add(node.val);
        return insertPos;
    }
}
