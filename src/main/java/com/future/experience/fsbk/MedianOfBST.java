package com.future.experience.fsbk;

import com.future.utils.TreeNode;

import java.util.List;

/**
 * Given a Binary Search Tree, find median of it.
 *
 * If no. of nodes are even: then median = ((n/2th node + (n+1)/2th node) /2
 * If no. of nodes are odd : then median = (n+1)/2th node.
 *
 *  Given BST(with odd no. of nodes) is :
 *                     6
 *                  /    \
 *                 3       8
 *               /   \    /  \
 *              1     4  7    9
 *
 * Inorder of Given BST will be : 1, 3, 4, 6, 7, 8, 9
 * So, here median will 6.
 *
 * Given BST(with even no. of nodes) is :
 *                     6
 *                  /    \
 *                 3       8
 *               /   \    /
 *              1     4  7
 *
 * Inorder of Given BST will be : 1, 3, 4, 6, 7, 8
 * So, here median will  (4+6)/2 = 5.
 *
 */
public class MedianOfBST {
    /**
     * - count the nodes in BST
     * - then do inorder traversal and find the
     *      - if count is odd, find count / 2 + 1
     *      - if count is even, find (count / 2 + count / 2 + 1) / 2
     * @param root
     * @return
     */
    public double findMedian(TreeNode root) {
        int cnt = count(root);
        helper(root, cnt / 2 + 1);
        if(cnt % 2 == 1) {
            return res[1];
        } else {
            return (double)(res[1] + res[0]) / 2.0;
        }
    }

    private int index = 1;
    private int[] res = new int[2];
    private void helper(TreeNode root, int k) {
        if(root == null) {
            return;
        }
        helper(root.left, k);
        if(index == k - 1) {
            res[0] = root.val;
        } else if(index == k) {
            res[1] = root.val;
            return;
        }
        index++;
        helper(root.right, k);

    }

    private int count(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return count(root.left) + count(root.right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
//        root.right.right = new TreeNode(9);
        MedianOfBST p = new MedianOfBST();
        System.out.println(p.findMedian(root));
    }
}
