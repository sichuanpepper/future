package com.future.round2;


import com.future.utils.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/description/
 *
 Given a binary tree, write a function to get the maximum width of the given tree.
 The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree,
 but some nodes are null.

 The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
 where the null nodes between the end-nodes are also counted into the length calculation.

 Example 1:
 Input:

          1
        /   \
       3     2
      / \     \
     5   3     9

 Output: 4
 Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 Example 2:
 Input:

      1
     /
    3
   / \
  5   3

 Output: 2
 Explanation: The maximum width existing in the third level with the length 2 (5,3).
 Example 3:
 Input:

       1
      / \
     3   2
    /
   5

 Output: 2
 Explanation: The maximum width existing in the second level with the length 2 (3,2).
 Example 4:
 Input:

             1
            / \
           3   2
          /     \
         5       9
        /         \
       6           7
 Output: 8
 Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


 Note: Answer will in the range of 32-bit signed integer.

 * Created by xingfeiy on 3/20/18.
 */
public class Problem662 {
    /**
     * Analyze: use Deque, beats 9%.
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int max = 0;
        while (!deque.isEmpty()) {
            //****ATTENTION: if the Deque is empty, the peek is always null.
            while (!deque.isEmpty() && deque.peekFirst() == null) deque.pollFirst();
            while (!deque.isEmpty() && deque.peekLast() == null) deque.pollLast();
            int size = deque.size();
            if(size < 1) break;
            max = Math.max(max, size);
            for(int i = 0; i < size; i++) {
                TreeNode tmp = deque.poll();
                if(tmp == null) {
                    deque.add(null);
                    deque.add(null);
                } else {
                    deque.add(tmp.left);
                    deque.add(tmp.right);
                }
            }
        }
        return max;
    }

    /**
     * Analyze: Any complete can be represented as array, and for each node nodes[i] in array, there are:
     * the left child of node i -> nodes[i * 2 + 1]
     * the right child of node i -> nodes[i * 2 + 2];
     * the leafs: nodes[length / 2 + 1, length - 1]
     * And we just need to find the leftmost and rightmost.
     *
     * Beats 96%
     * @param root
     * @return
     */
    public int dfs(TreeNode root) {
        if(root == null) return 0;
        return helper(root, 0, 1, new ArrayList<>());
    }

    /**
     *
     * @param node
     * @param level
     * @param index
     * @param starts
     * @return the index of node in the array.
     */
    private int helper(TreeNode node, int level, int index, List<Integer> starts) {
        if(node == null) return 0;
        if(starts.size() == level) starts.add(index); //the start index of given level hasn't be set.
        //now, find the max range, it could be start from starts.get(level) to either the position of node self, or position of left or right child.
        int cur = index - starts.get(level) + 1;
        int left = helper(node.left, level + 1, index * 2, starts);
        int right = helper(node.right, level + 1, index * 2 + 1, starts);
        return Math.max(Math.max(left, right), cur);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        Problem662 p = new Problem662();
        System.out.println(p.widthOfBinaryTree(root));
    }
}
