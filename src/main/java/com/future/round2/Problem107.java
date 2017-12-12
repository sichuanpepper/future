package com.future.round2;

import com.future.utils.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
 *
 *
 Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 (ie, from left to right, level by level from leaf to root).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
  3
 / \
 9  20
 /  \
 15   7
 return its bottom-up level order traversal as:
 [
 [15,7],
 [9,20],
 [3]
 ]

 * Created by xingfeiy on 12/11/17.
 */
public class Problem107 {
    /**
     * Analyze:
     * It's a similar problem with problem 102, the problem is up-bottom, and this one is bottom-up.
     * So the simple way is do it up-bottom as problem 102, and reverse the result to return...
     * But, actually, we don't have to reverse result at the end, we just need always insert new sub-result at index 0...
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmpList = new ArrayList<>(size);
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                tmpList.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(0, tmpList);
        }
        return res;
    }

}
