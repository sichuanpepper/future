package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/path-sum-iii/description/
 *
 You are given a binary tree in which each node contains an integer value.

 Find the number of paths that sum to a given value.

 The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

 The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

 Example:

 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

               10
              /  \
             5   -3
            / \    \
           3   2   11
          / \   \
         3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11


 ==============================
              10
             /  \
            5   -2
           / \    \
          3   2   2
         / \   \   \
        3  -2   1  -2
 [TAGS] TREE

 * Created by xingfeiy on 12/11/17.
 *
 */
public class Problem437 {
    /**
     * Analyze:
     * There are 3 situations for any sub tree
     * - the sum path start from itself.
     * - the sum path start from left child.
     * - the sum path start from right child.
     *
     * So, we just need find how many solutions for each situation.
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        return findInPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int findInPath(TreeNode node, int sum) {
        if(node == null) return 0;
        return ((node.val == sum) ? 1 : 0) + findInPath(node.left, sum - node.val) + findInPath(node.right, sum - node.val);
    }
}
