package com.future.experience.fsbk;

import com.future.utils.TreeNode;

import java.util.*;

public class AllNodesDistanceKinBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<TreeNode> path = new ArrayList<>();
        findAllParents(root, target, new ArrayList<>(), path);
        Set<Integer> res = new HashSet<>();
        for(int i = path.size() - 1; i >= path.size() - K - 1; i--) {
            TreeNode p = path.get(i);
            if(i == (path.size() - K - 1)) {
                res.add(p.val);
            } else {
                res.addAll(findChildren(p, K - (path.size() - i  -1)));
            }
        }
        return new ArrayList<>(res);
    }

    public void findAllParents(TreeNode root, TreeNode target, List<TreeNode> curRes, List<TreeNode> res) {
        if(root == null) {
            return;
        }
        curRes.add(root);
        if(root.val == target.val) {
            res.addAll(curRes);
            return;
        }
        findAllParents(root.left, target, curRes, res);
        findAllParents(root.right, target, curRes, res);
        curRes.remove(curRes.size() - 1);
    }

    private Set<Integer> findChildren(TreeNode root, int distance) {

        Set<Integer> res = new HashSet<>();
        if(root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            if(distance == 0) {
                for(int i = 0; i < size; i++) {
                    res.add(queue.poll().val);
                }
                break;
            } else {
                for(int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if(node.left != null) {
                        queue.offer(node.left);
                    }
                    if(node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        AllNodesDistanceKinBinaryTree p = new AllNodesDistanceKinBinaryTree();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        System.out.println(p.distanceK(root, root.left.right, 1));

    }
}
