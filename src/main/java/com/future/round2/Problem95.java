package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/unique-binary-search-trees-ii/description/
 *
 Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

 For example,
 Given n = 3, your program should return all 5 unique BST's shown below.

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3

 * Created by xingfeiy on 12/10/17.
 */
public class Problem95 {
    /**
     * Analyze:
     * If there's one node, only one BST
     * If there are two nodes, two BSTs
     * If there are 3 nodes, 5 BSTs, as we can see
     * when 1 is head, there are two nodes which are greater than 1, so these two number come to right, so there are two BSTs are heading by 1.
     * When 2 is head, there is one node which is greater than 2 and one node smaller than 2,
     * so each side has one BST, so there is one BST is heading by 2.
     * When 3 is head, similar as 1.
     *
     * So f(n) = f(n - 1) * f(0) + f(n - 2) * f(1) + f(n - 3) * f(2)...  where f(0) = 1
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n < 1) return new ArrayList<>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if(start > end) {
            res.add(null);
            return res;
        }
        if(start == end) {
            res.add(new TreeNode(start));
            return res;
        }

        for(int i = start; i <= end; i++) {
            List<TreeNode> lefts = helper(start, i - 1);
            List<TreeNode> rights = helper(i + 1, end);
            for(TreeNode left : lefts) {
                for(TreeNode right : rights) {
                    TreeNode head = new TreeNode(i);
                    head.left =left;
                    head.right = right;
                    res.add(head);
                }
            }
        }
        return res;
    }


}
