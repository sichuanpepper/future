package com.future.round2;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xingfeiy on 12/11/17.
 *
 Given a binary tree, return the zigzag level order traversal of its nodes' values.
 (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
     3
    / \
   9  20
     /  \
    15   7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]
 */
public class Problem103 {
    /**
     * Analyze:
     * It's similar issue with problem 102, we also use a queue to do that, only difference is switch direction each time.
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean fromLeft = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmpRes = new ArrayList<>();
            List<TreeNode> tmpNodes = new ArrayList<>(queue.size());
            for(int i = 0; i < size; i++) {
                tmpNodes.add(queue.poll());
            }
            for(int i = size - 1; i >= 0; i--) {
                tmpRes.add(tmpNodes.get(i).val);
                if(fromLeft) {
                    if(tmpNodes.get(i).left != null) queue.offer(tmpNodes.get(i).left);
                    if(tmpNodes.get(i).right != null) queue.offer(tmpNodes.get(i).right);
                } else {
                    if(tmpNodes.get(i).right != null) queue.offer(tmpNodes.get(i).right);
                    if(tmpNodes.get(i).left != null) queue.offer(tmpNodes.get(i).left);
                }
            }

            fromLeft = !fromLeft;
            res.add(tmpRes);
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root)
    {
        List<List<Integer>> sol = new ArrayList<>();
        travel(root, sol, 0);
        return sol;
    }

    private void travel(TreeNode curr, List<List<Integer>> sol, int level)
    {
        if(curr == null) return;

        if(sol.size() <= level)
        {
            List<Integer> newLevel = new LinkedList<>();
            sol.add(newLevel);
        }

        List<Integer> collection  = sol.get(level);
        if(level % 2 == 0) collection.add(curr.val);
        else collection.add(0, curr.val);

        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }
}
