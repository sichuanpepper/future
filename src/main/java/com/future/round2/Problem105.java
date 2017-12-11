package com.future.round2;


import com.future.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *
 Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.
 *
 * Created by xingfeiy on 12/11/17.
 */
public class Problem105 {
    /**
     * Analyze:
     *    1
     *  /   \
     * 2     3
     *
     * preorder 1, 2, 3
     * inorder 2, 1, 3
     *
     * 1) All elements in preorder are root or sub-root.
     * 2) Generate left && right child for roots in preorder one by one.
     * 3) Once all elements in preorder have been processed, the tree is done.
     *
     * How to generate tree for each root?
     *  1) Find position in inorder, if the left position is valid, that's left child, same thing for right side.
     *  2) We have to search element in inorder each time, so we use a HashMap to store inorder array.
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null) return null;
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return helper(0, 0, inorder.length - 1, preorder, inorderMap);
    }

    private TreeNode helper(int preIndex, int startIn, int endIn, int[] preorder, Map<Integer, Integer> inorderMap) {
        if(startIn > endIn) return null;
        TreeNode root = new TreeNode(preorder[preIndex]);
        int rootInInorder = inorderMap.get(preorder[preIndex]);
        // that means there are rootInInorder elements in left of root, use this value we can find where's the right child of root in preorder.
        root.left = helper(preIndex + 1, startIn, rootInInorder - 1, preorder, inorderMap);
        root.right = helper(preIndex + rootInInorder - startIn + 1, rootInInorder + 1, endIn,preorder, inorderMap);
        return root;
    }

}
