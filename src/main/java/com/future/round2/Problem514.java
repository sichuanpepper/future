package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
 *

 You need to find the largest value in each row of a binary tree.

 Example:
 Input:

        1
       / \
      3   2
     / \   \
    5   3   9

 Output: [1, 3, 9]

 * Created by xingfeiy on 1/7/18.
 */
public class Problem514 {
    /**
     * Analyze:
     * The straightforward way is do the BFS and store the largest value in each row.
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            int maxVal = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                maxVal = Math.max(maxVal, node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(maxVal);
        }
        return res;
    }
}
