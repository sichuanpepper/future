package com.future.foundation.tree;


import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * The problem is:
 *
 * We are given a binary tree, and we need to find the path between node A and node B, and store this path into a list.
 * If can't find a path, return an empty list.
 *
 * Created by xingfeiy on 12/10/17.
 */
public class FindPath {
    /**
     * Analyze:
     * - root could be null
     * - Either nodeA or nodeB may not existed.
     * - nodeA may equal to nodeB
     *
     * if root == nodeA, find nodeB in root.left and root.right and return the path.
     * if root == nodeB, find nodeA in root.left and root.right and return the path.
     * else find nodeA and node B in both root.left and root.right recursively
     *
     * @param root
     * @param nodeA
     * @param nodeB
     * @return
     */
    public List<TreeNode> findPath(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
        return new ArrayList<>();
    }


}
