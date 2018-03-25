package com.future.round2;

import com.future.utils.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/

 Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

 Example 1:
 Input:
       5
      / \
     3   6
    / \   \
   2   4   7

 Target = 9

 Output: True
 Example 2:
 Input:
        5
       / \
      3   6
     / \   \
    2   4   7

 Target = 28

 Output: False

 * Created by xingfeiy on 3/23/18.
 */
public class Problem653 {
    /**
     * Analyze: Any traversal way, then two sum.
     * inorder: beats 45%
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        return inorder(root, new HashSet<>(), k);
    }

    private boolean inorder(TreeNode node, Set<Integer> values, int k) {
        if(node == null) return false;
        if(inorder(node.left, values, k)) return true;
        if(values.contains(k - node.val)) return true;
        values.add(node.val);
        return inorder(node.right, values, k);
    }


}
