package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 Both the left and right subtrees must also be binary search trees.
 For example:
 Given BST [1,null,2,2],
 1
 \
 2
 /
 2
 return [2].

 Note: If a tree has more than one mode, you can return them in any order.

 Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 * Created by someone on 11/19/17.
 */
public class Problem501 {
    /**
     * If we don't consider the follow up question, we can traversal the tree and use a map to store the count of each element.
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        List<Integer> res = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == max) res.add(entry.getKey());
        }
        int[] array = new int[res.size()];
        int index = 0;
        for(int val : res) array[index++] = val;
        return array;
    }

    private int max = 0;
    private void helper(TreeNode node, Map<Integer, Integer> map) {
        if(node == null) return;
        map.put(node.val, map.getOrDefault(node.val, 0) + 1);
        max = Math.max(max, map.get(node.val));
        helper(node.left, map);
        helper(node.right, map);
    }
}
