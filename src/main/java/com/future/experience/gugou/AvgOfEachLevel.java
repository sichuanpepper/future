package com.future.experience.gugou;

import com.future.utils.BTreePrinter;
import com.future.utils.DisplayUtils;
import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 给一个二叉树 按层求均值. 要BFS 和DFS两种写法.
 *
 * The BFS is much straightforward for this problem.
 * If we want to do it with BFS, we can use a list to maintain the information of each level, the information includes number of node and the sum of node.
 */
public class AvgOfEachLevel {
    public List<Double> avgBFS(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Double> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            int total = 0;
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                total += node.val;
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add((double)total / (double)size);
        }
        return res;
    }

    public List<Double> avgDFS(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<int[]> cntAndSum = new ArrayList<>();
        helper(root, cntAndSum, 0);
        return cntAndSum.stream().filter(obj -> obj[0] != 0).map(obj -> (double)obj[1]/(double)obj[0]).collect(Collectors.toList());
    }

    //int[0]: count, int[1]: sum
    private void helper(TreeNode node, List<int[]> res, int level) {
        if(node == null) {
            return;
        }
        if(level >= res.size()) {
            res.add(new int[]{0, 0});
        }
        helper(node.left, res, level + 1);
        res.get(level)[0]++;
        res.get(level)[1] += node.val;
        helper(node.right, res, level + 1);
    }

    public static void main(String[] args) {
        AvgOfEachLevel p = new AvgOfEachLevel();
        BTreePrinter.printNode(TreeNode.getSample3());
        DisplayUtils.printList(p.avgBFS(TreeNode.getSample3()));
        DisplayUtils.printList(p.avgDFS(TreeNode.getSample3()));
    }
}
