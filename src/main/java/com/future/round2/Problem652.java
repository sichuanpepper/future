package com.future.round2;

import com.future.utils.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/find-duplicate-subtrees/description/
 *
 Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees,
 you only need to return the root node of any one of them.

 Two trees are duplicate if they have the same structure with same node values.

 Example 1:
            1
           / \
          2   3
         /   / \
        4   2   4
           /
          4
 The following are two duplicate subtrees:
     2
    /
   4
 and
  4
 Therefore, you need to return above trees' root in the form of a list.

 * Created by xingfeiy on 3/20/18.
 */
public class Problem652 {
    /**
     * Analyze:
     * The straightforward way is just traversal subtrees from root to leafs to check the duplicated subtrees.
     * But the performance is not good since the subtrees will be computed many times.
     *
     * So we consider bottom-up way instead up-bottom way, the postorder seems a way to do that.
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if(root == null) return res;
        helper(root, new HashMap<>(), res);
        return res;
    }

    private String helper(TreeNode node, Map<String, Integer> subtrees, List<TreeNode> res) {
        if(node == null) return "$";
        String str = "";
        str += "/" + helper(node.left, subtrees, res);
        str += "/" + helper(node.right, subtrees, res);
        str += "/" + node.val;
        if(subtrees.containsKey(str) && subtrees.get(str) == 1) {
            res.add(node);
        }
        subtrees.put(str, subtrees.getOrDefault(str, 0) + 1);

        return str;
    }
}
