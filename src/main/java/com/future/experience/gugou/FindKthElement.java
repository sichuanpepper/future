package com.future.experience.gugou;

import com.future.utils.BTreePrinter;
import com.future.utils.TreeNode;

/**
 *  find kth item.
 *  这里的K 是可变的。有insert() & getKth() 操作。insert() 频繁的时候怎么写， get() 频繁的时候应该怎么写 （讨论）。 最后写出两个操作都是均等的，什么复杂度最合适 -> O（logn) tree
 *
 *
 *  // if insertion is heavy - just insert element into an array with TC O(1), and we can use quick select in the get method with avg TC O(n)
 *  // if get is heavy -> insert it into a sorted array with TC (O(n)), and get with TC O(1)
 *  // if both insertion and getting are heavy, consider BST, insert and get are O(h)
 *  // to improve the performance of BST, balance the tree, in-order traversal to get a sorted array, then generate BST by this array.
 */
public class FindKthElement {
    private TreeNode root = null;

    public void insert(int val) {
        this.root = insert(this.root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if(node == null) {
            node = new TreeNode(val);
            return node;
        }
        if(node.val > val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }
        return node;
    }

    public int getKth(int k) {
//        BTreePrinter.printNode(this.root);
        int[] res = new int[]{k, 0};
        search(this.root, res);
        return res[1];
    }

    private void search(TreeNode node, int[] res) {
        if(node.left != null) search(node.left, res);
        if(--res[0] == 0) {
            res[1] = node.val;
            return;
        }
        if(node.right != null) search(node.right, res);
    }



    /**
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return 0;
    }

    public static void main(String[] args) {
        FindKthElement p = new FindKthElement();
        p.insert(2);
        p.insert(5);
        p.insert(3);
        p.insert(4);
        p.insert(1);



        System.out.println(p.getKth(1));
        System.out.println(p.getKth(2));
        System.out.println(p.getKth(3));
        System.out.println(p.getKth(4));
        System.out.println(p.getKth(5));
    }
}
