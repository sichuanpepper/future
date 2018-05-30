package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the
 length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

 Example:
 Given a binary tree
      1
     / \
    2   3
   / \
  4   5
 Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

 Note: The length of path between two nodes is represented by the number of edges between them.

 * Created by xingfeiy on 5/21/18.
 */
public class Problem543 {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return max;
    }

    private int max = 0;
    private int helper(TreeNode node) {
        if(node == null) return 0;
        int left = (node.left == null) ? 0 : helper(node.left) + 1;
        int right = (node.right == null) ? 0 : helper(node.right) + 1;
        max = Math.max(max,left + right);
        return Math.max(left, right);
    }

    /**
     * The follow up question, print the diameter paths.
     * @param root
     * @return
     */
    public int diameterOfBinaryTreeFollowup(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return max;
    }

    private List<Integer> maxPath = new ArrayList<>();

    private List<Integer> helperFollowup(TreeNode node) {
        if(node == null) return new ArrayList<>();
        List<Integer> left = (node.left == null) ? new ArrayList<>() : helperFollowup(node.left);
        List<Integer> right = (node.right == null) ? new ArrayList<>() : helperFollowup(node.right);
        if(left.size() + right.size() > maxPath.size()) {
            maxPath.clear();
            maxPath.addAll(left);
            maxPath.addAll(right);
        }
        if(left.size() > right.size()) {
            left.add(node.val);
            return left;
        }
        right.add(node.val);
        return right;
    }
}
