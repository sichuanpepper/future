package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/

 Given a binary tree, return the postorder traversal of its nodes' values.

 For example:
 Given binary tree [1,null,2,3],

   1
    \
    2
   /
  3


 return [3,2,1].

 Note: Recursive solution is trivial, could you do it iteratively?

 * Created by xingfeiy on 3/25/18.
 */
public class Problem145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if(p != null) {
                stack.push(p);
                res.add(0, p.val);
                p = p.right;
            } else {
                p = stack.pop().left;
            }
        }
        return res;
    }
}
