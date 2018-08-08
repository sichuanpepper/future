package com.future.foundation.algo;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Most time, the tree problems can be solved by DFS, BFS, recursive
 *
 * Created by xingfeiy on 8/2/18.
 */
public class TreeProblems {
    /**
     * You need to find the largest value in each row of a binary tree.
     *
     * Analyze: the solution is pretty straightforward, just simply traversal nodes row by row, that's how BFS works.
     *
     * @param root
     * @return
     */
    public List<Integer> largestValuesBFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                max = Math.max(max, tmp.val);
                if(tmp.left != null) queue.offer(tmp.left);
                if(tmp.right != null) queue.offer(tmp.right);
            }
            res.add(max);
        }
        return res;
    }

    /**
     * You need to find the largest value in each row of a binary tree.
     *
     * Analyze: for DFS, the pre-order will traversal all nodes from root to each leaf in vertical direction.
     * When we traversal each path from root to leaf, we keep updating res.
     * @param root
     * @return
     */
    public List<Integer> largestValuesDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        largestValuesDFSHelper(root, res, 0);
        return res;
    }

    private void largestValuesDFSHelper(TreeNode root, List<Integer> res, int curIndex) {
        if(root == null) return;
        if(res.size() < curIndex + 1) {
            res.add(root.val);
        } else {
            res.set(curIndex, Math.max(res.get(curIndex), root.val));
        }
        largestValuesDFSHelper(root.left, res, curIndex + 1);
        largestValuesDFSHelper(root.right, res, curIndex + 1);
    }

    /**
     * https://leetcode.com/problems/unique-binary-search-trees/description/
     *
     * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
     *
     * Analyze:
     *  - Actually we are given a sequence integers sorted in ascending order.
     *  - Each number can be root, and the left range produces the left subtrees, and right range produces right subtrees.
     *  - For each root, will produce left subtrees * right subtrees.
     *  - To produce left subtrees and right subtrees, it's D & C problem.
     *  - How to produce subtree? Just reduce the big problem to small problem.
     *      - If we have zero node, produce a null tree.
     *      - If we have 1 node, produce a tree with one node.
     *      - If we have 2 nodes,
     *          - if 1 is root, then left subtree is null and right subtree is 2
     *          - if 2 is root, then left subtree is 1 and right subtree is null
     *      - ...
     *
     *
     * @param n
     * @return
     */
    public int numTreesDC(int n) {
        if(n < 1) return 0;
        return numTreesHelper(1, n);
    }

    private int numTreesHelper(int start, int end) {
        if(start > end) return 0;
        if(start == end) return 1;

        int res = 0;
        for(int i = start; i <= end; i++) {
            int left = numTreesHelper(start, i - 1);
            int right = numTreesHelper(i + 1, end);
            if(left > 0 && right > 0) {
                res += left * right;
            } else {
                res += Math.max(left, right);
            };
        }
        return res;
    }

    /**
     * The above solution can generate all results, but if we just want to know how many unique BSTs, this way is too heavy.
     *
     * To compute how many unique BSTs can be generated, it only depends one how many numbers we have, and it doesn't number what numbers we have.
     * Likes 1, 2, 3 and 4, 5, 6 will generate same amount unique BSTs.
     * And it's easy to see there are a lot of duplicated computes in the above solution, so it seems DP is a way to go.
     * f(n) = f(0) * f(n - 1) + f(1) * f(n - 2) + ...
     * That means, each number can be root, and as the analysis above, the left range goes to left subtree and right range goes to right subtree.
     * To get f(n), we just need to know f(0) to f(n - 1).
     * for f(0), that means there's 0 node, that can produce only one BST, that is null, so
     * f(0) = 1
     * f(1) = f(0) * f(0) = 1
     * f(2) = f(0) * f(1) + f(1) * f(0) = 2
     * f(3) = f(0) * f(2) + f(1) * f(1) + f(2) * f(1) = 1 * 2 + 1 * 1 + 2 * 1 = 5
     *
     * @param n
     * @return
     */
    public int numTreesDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

    /**
     *
     Given a binary tree, count the number of uni-value subtrees.

     A Uni-value subtree means all nodes of the subtree have the same value.
     * @param root
     * @return
     */
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return count;
    }

    private int count = 0;
    private boolean helper(TreeNode node) {
        if(node.left == null || node.right == null) {
            count++;
            return true;
        }
        if(helper(node.left) && helper(node.right) && node.left.val == node.right.val) {
            count++;
            return true;
        }
        return false;
    }


    /**
     * Given a binary tree, find the length of the longest consecutive sequence path.

     The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
     The longest consecutive path need to be from parent to child (cannot be the reverse).
     * Analyze:
     * - The problem is finding the longest consecutive sequence from parent to child, the straightforward way is DFS.
     * - If the current value = prv value + 1, then current length + 1, and updates the longest value.
     * - Otherwise, start it from 1.
     * @param root
     * @return
     */
    public int longestConsecutive(TreeNode root) {
        return 0;
    }

    public static void main(String[] args) {
        TreeProblems t = new TreeProblems();
        System.out.println(t.numTreesDC(3));
        System.out.println(t.numTreesDP(3));


        System.out.println("Testing count of unival substrees");
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        System.out.println(t.countUnivalSubtrees(root));
    }

}
