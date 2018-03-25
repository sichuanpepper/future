package com.future.round1;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets.

 Note: The solution set must not contain duplicate subsets.

 For example,
 If nums = [1,2,3], a solution is:

 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]

 * Created by someone on 8/30/17.
 */
public class Subset78 {
    public List<List<Integer>> subsets(int[] nums) {
        return solution1(nums);
    }

    private List<List<Integer>> solution1(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null || nums.length < 1) {
            return res;
        }
        dfs(nums, 0, res, new ArrayList());
        return res;
    }

    /**
     * The DFS solution, traversal the tree by DFS, and there are two possibilities for each node, included or excluded.
     *
     *             null
     *        /           \
     *       null           1
     *     /    \         /   \
     *   null     2      null   2
     *   /  \   /   \     /       \
     * null  3 null  3   .........
     *
     * @param nums
     * @param index
     * @param res
     * @param cur
     */
    private void dfs(int[] nums, int index, List<List<Integer>> res, List<Integer> cur) {
        if(index >= nums.length) {
            res.add(new ArrayList(cur));
            return;
        }

        //excluded current node, likes the left child.
        dfs(nums, index + 1, res, cur);
        //included current node, likes the right child.
        cur.add(nums[index]);   //likes we got the right value.
        dfs(nums, index + 1, res, cur);

        cur.remove(cur.size() - 1);
    }

    private void practiceDFS(MyTreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res, new ArrayList<>());
        for(List<Integer> list : res) {
            DisplayUtils.printList(list);
        }
    }

    private void backtrackBT(MyTreeNode root) {}

    private void btHelper(MyTreeNode node, List<List<Integer>> res, List<Integer> cur) {
        if(node == null) {
            return;
        }

        res.add(new ArrayList<>(cur)); //
        //how to simulate for loop? We don't know its adjacent nodes.

    }

    private void helper(MyTreeNode node, List<List<Integer>> res, List<Integer> cur) {
        //when stop the recursive and do something
        // here: break the recursive if the node is a leaf.
        // add it to results.
        if(node.left == null && node.right == null) {
            List<Integer> tmp = new ArrayList<>(cur);
            tmp.add(node.val);
            res.add(tmp);
            return;
        }

        cur.add(node.val);
        helper(node.left, res, cur);
        helper(node.right, res, cur);
        cur.remove(cur.size() - 1);
    }

    public static void main(String[] args) {
        new Subset78().practiceDFS(MyTreeNode.getSample());
    }
}
