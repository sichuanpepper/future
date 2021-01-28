package com.future.round2;

import com.future.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/discuss/
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.

    Note:
    You may assume that duplicates do not exist in the tree.
 * Created by someone on 11/8/17.
 */
public class Problem106 {
    /**
     * Analyze:
     * 1. The last element in postorder which is the root.
     * 2. Find that last element in inorder, it divided the inorder into left part and right part.
     * 3. Do the same thing for left part and right part.
     * 4. Because we need to find the last element of postorder in inorder array each time, so we can use a map to cache inorder.
     * @param inorder
     * @param postorder
     * @return
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> iMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            iMap.put(inorder[i], i);
        }
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, iMap);
    }

    private static TreeNode helper(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd, Map<Integer, Integer> iMap) {
        if(iStart < 0 || iStart > iEnd || iEnd >= inorder.length || pStart < 0 || pStart > pEnd || pEnd >= postorder.length) return null;
        TreeNode root = new TreeNode(postorder[pEnd]);
        if(iStart >= iEnd || pStart >= pEnd) return root;
        int iPosition = iMap.get(postorder[pEnd]);
        root.left = helper(inorder, iStart, iPosition - 1, postorder, pStart, pStart + iPosition - iStart - 1, iMap);
        root.right = helper(inorder, iPosition + 1, iEnd, postorder, pEnd - (iEnd - iPosition), pEnd - 1, iMap);
        return root;
    }
}
