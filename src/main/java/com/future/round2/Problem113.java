package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/description/
 *
 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 For example:
 Given the below binary tree and sum = 22,
       5
      / \
     4   8
    /   / \
   11  13  4
  /  \    / \
 7    2  5   1
 return
 [
 [5,4,11,2],
 [5,8,4,5]
 ]

 * Created by xingfeiy on 12/11/17.
 */
public class Problem113 {
    /**
     * Analyze:
     * DFS...
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        helper(root, sum, 0,new ArrayList<>(), res);
        return res;
    }

    private void helper(TreeNode node, int sum, int curSum,List<Integer> curPath, List<List<Integer>> res) {
        if(node == null) return;
        curSum += node.val;
        curPath.add(node.val);
        if(node.left == null && node.right == null) {
            if(sum == curSum) res.add(new ArrayList<>(curPath));
            return;
        }
        if(node.left != null ) {
            helper(node.left, sum, curSum, curPath, res);
            curPath.remove(curPath.size() - 1);
        }
        if(node.right != null) {
            helper(node.right, sum, curSum, curPath, res);
            curPath.remove(curPath.size() - 1);
        }

    }
}
