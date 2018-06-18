package com.future.round2;

import com.future.utils.TreeNode;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
 *
 *
 Given a non-empty special binary tree consisting of nodes with the non-negative value,
 where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes,
 then this node's value is the smaller value among its two sub-nodes.

 Given such a binary tree, you need to output the second minimum value in the set of all the nodes' value in the whole tree.

 If no such second minimum value exists, output -1 instead.

 Example 1:
 Input:
     2
    / \
   2   5
      / \
     5   7

 Output: 5
 Explanation: The smallest value is 2, the second smallest value is 5.
 Example 2:
 Input:
   2
  / \
 2   2

 Output: -1
 Explanation: The smallest value is 2, but there isn't any second smallest value.

 * Created by someone on 11/30/17.
 */
public class Problem671 {
    /**
     * Analyze:
     * The node's value is the smaller value among its two sub-nodes, so the root is the smallest one.
     *
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null || root.left == null || root.right == null) return -1;
        int left = root.left.val;
        if(left == root.val) {
            left = findSecondMinimumValue(root.left);
        }
        int right = root.right.val;
        if(right == root.val) {
            right = findSecondMinimumValue(root.right);
        }

        if(left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left == -1) {
            return right;
        } else {
            return left;
        }
    }


}
