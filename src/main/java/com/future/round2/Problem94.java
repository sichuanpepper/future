package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 *
 Given a binary tree, return the inorder traversal of its nodes' values.

 For example:
 Given binary tree [1,null,2,3],
 1
 \
 2
 /
 3
 return [1,3,2].

 Note: Recursive solution is trivial, could you do it iteratively?
 *
 * Created by xingfeiy on 12/10/17.
 */
public class Problem94 {
    /**
     * Analyze:
     * It's easy to use recursive solution to get inorder traversal, but this problem asked no recursive solution.
     * The recursive solution actually uses stack, so we can also use stack to simulate recursive solution.
     * Let's see how recursive works for this problem first.
     *
     * inorderTraversal(root.left);
     * list.add(root.val);
     * inorderTraversal(root.right);
     *
     * So basically, the recursive push the leftmost nodes into stack in first lien,
     * and then pop the last leftmost node out do some business in second line,
     * at last, push right node of the node pop out from second line.
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        TreeNode pointer = root;
        Stack<TreeNode> stack = new Stack<>();


        while (pointer != null || !stack.isEmpty()) {
            //simulate inorderTraversal(root.left)
            while (pointer != null) {
                stack.add(pointer);
                pointer = pointer.left;
            }

            TreeNode tmp = stack.pop();
            res.add(tmp.val); //simulate list.add(root.val) in recursive.

            //simulate inorderTraversal(root.right)
            pointer = tmp.right;
        }
        return res;
    }
}
