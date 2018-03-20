package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/description/

 Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 Return the root node reference (possibly updated) of the BST.

 Basically, the deletion can be divided into two stages:

 Search for a node to remove.
 If the node is found, delete the node.
 Note: Time complexity should be O(height of tree).

 Example:

 root = [5,3,6,2,4,null,7]
 key = 3

     5
    / \
   3   6
  / \   \
 2   4   7

 Given key to delete is 3. So we find the node with value 3 and delete it.

 One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

      5
     / \
    4   6
   /     \
  2       7

 Another valid answer is [5,2,6,null,4,null,7].

       5
      / \
     2   6
     \   \
     4   7
[TAGS] TREE
 * Created by xingfeiy on 1/7/18.
 */
public class Problem450 {
    /**
     * Analyze:
     * The given key could be the left subtree or right subtree, or equal to root itself.
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val > key) { //do the business in left subtree.
            root.left = deleteNode(root.left, key);
        } else if(root.val < key) { // do the business in right subtree.
            root.right = deleteNode(root.right, key);
        } else { //equals to root itself.
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;
            //find the minimum node in right subtree
            // 1. why is finding the minimum node in right subtree rather than maximum node in right subtree? or either way is ok?
            // 2. after the minimum node is found, there are two strategies:
            //     - minimum_node.left = root.left
            //     - set the root.val to minimum val, then call deleteNode(root.right, root.val) to delete minimum node in right subtree.
            //     - the first way is simple, but could change the height of tree.
            TreeNode minRightNode = root.right;
            while(minRightNode.left != null) minRightNode = minRightNode.left;
            minRightNode.left = root.left;
            root = root.right;
        }
        return root;
    }
}
