package com.future.foundation.tree;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by xingfeiy on 4/4/18.
 */
public class IterativelyDFS {

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            if(node == null) node = stack.pop();
            while(node != null) {
                res.add(node.val);
                if(node.right != null) stack.push(node.right);
                node = node.left;
            }
        }
        return res;
    }

    public static List<Integer> preorderTraversalV2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                res.add(node.val);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
        return res;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
        return res;
    }

    public static List<Integer> inorderTraversalV2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                res.add(node.val);
                node = node.right;
            }
        }
        return res;
    }

    /**
     * Analyze: The common pre-order way is current first , and then left, and last right
     * If we change this pre-order way a little bit, current first, and then right, and last left, we will get the
     * reverse order of post-order.
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                res.add(0, node.val);
                node = node.right;
            } else {
                node = stack.pop().left;
            }
        }
        return res;
    }
}
