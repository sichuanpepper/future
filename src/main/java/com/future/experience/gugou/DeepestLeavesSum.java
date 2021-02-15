package com.future.experience.gugou;

import com.future.utils.BTreePrinter;
import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/deepest-leaves-sum/
 */
public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        int[] res = new int[2];
        helper(root, 0, res);
        return res[1];
    }

    // curRes[0]: deepest level, curRes[1]: current sum
    private void helper(TreeNode root, int curLevel, int[] curRes) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            if(curLevel > curRes[0]) {
                //overwirte
                curRes[0] = curLevel;
                curRes[1] = root.val;
            } else if(curLevel == curRes[0]) {
                curRes[1] += root.val;
            }
            return;
        }
        helper(root.left, curLevel + 1, curRes);
        helper(root.right, curLevel + 1, curRes);
    }

    /**
     * 找一个二叉树的最深的树节点, 如果有很多, 找最右边的那个
     * @param root
     * @return
     */
    public int findDeepestValFromRight(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list, 0);
        return list.get(list.size() - 1);
    }

    private void helper(TreeNode root, List<Integer> list, int level) {
        if(root == null) {
            return;
        }

        if(level >= list.size()) {
            list.add(root.val);
        }
        helper(root.right, list, level + 1);
        helper(root.left, list,level + 1);
    }

    public static void main(String[] args) {
        DeepestLeavesSum p = new DeepestLeavesSum();
        System.out.println(p.findDeepestValFromRight(TreeNode.getSample3()));
        System.out.println(p.findDeepestValFromRight(TreeNode.getSample2()));
        System.out.println(p.findDeepestValFromRight(TreeNode.getSample1()));
    }
}
