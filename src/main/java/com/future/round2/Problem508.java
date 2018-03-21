package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/most-frequent-subtree-sum/description/
 Given the root of a tree, you are asked to find the most frequent subtree sum.
 The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node
 (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values
 with the highest frequency in any order.

 Examples 1
 Input:

     5
   /  \
  2   -3
 return [2, -3, 4], since all the values happen only once, return all of them in any order.
 Examples 2
 Input:

     5
   /  \
  2   -5
 return [2], since 2 happens twice, however -5 only occur once.
 Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 *
 * Created by xingfeiy on 3/20/18.
 */
public class Problem508 {
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        List<Integer> res = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == max) res.add(entry.getKey());
        }

        int[] array = new int[res.size()];
        for(int i = 0; i < res.size(); i++) array[i] = res.get(i);
        return array;

    }
    private int max = 0;
    private int helper(TreeNode node, Map<Integer, Integer> map) {
        if(node == null) return 0;
        int sum = node.val + helper(node.left, map) + helper(node.right, map);
        int count = map.getOrDefault(sum, 0) + 1;
        max = Math.max(max, count);
        map.put(sum, count);
        return sum;
    }
}
