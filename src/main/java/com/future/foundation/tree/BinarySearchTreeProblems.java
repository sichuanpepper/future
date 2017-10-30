package com.future.foundation.tree;

/**
 * Created by someone on 6/5/17.
 */
public class BinarySearchTreeProblems {
    public static class Node {
        public int val;

        public Node left = null;

        public Node right = null;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node insert(Node tree, int val) {
        if(tree == null) {
            return new Node(val);
        }

        if(val <= tree.val) {
            tree.left = insert(tree.left, val);
        } else {
            tree.right = insert(tree.right, val);
        }
        return tree;
    }



    public static void printInorder(Node tree) {
        if(tree == null) {
            return;
        }

        printInorder(tree.left);
        System.out.print(tree.val + " ");
        printInorder(tree.right);
    }

    public static int countNodes(Node tree) {
        if(tree == null) {
            return 0;
        }

        return countNodes(tree.left) + countNodes(tree.right) + 1;
    }

    public static int countLeafs(Node tree) {
        if(tree == null) {
            return 0;
        }

        if(tree.left == null && tree.right == null) {
            return 1;
        }

        return countLeafs(tree.left) + countLeafs(tree.right);
    }

    /**
     * There are two conventions to define height of Binary Tree
     *   1) Number of nodes on longest path from root to the deepest node.
     *   2) Number of edges on longest path from root to the deepest node.
     *   In this post, the first convention is followed, it seems that the first convention is more popular.
     *   As convention 1, and leafs have height 1.
     * @param root
     * @return
     */
    public static int treeHeight(Node root){
        if(root == null) {
            return 0;
        }
        return (1+ Math.max(treeHeight(root.left),treeHeight(root.right)));
    }





    public static void main(String[] args) {
        Node head = null;
        head = insert(head, 4);
        insert(head, 5);
        insert(head, 8);
        insert(head, 2);
        insert(head, 1);
        insert(head, 3);

        printInorder(head);

        System.out.println();

        System.out.println("The leafs are : " + countLeafs(head));

        System.out.println(countNodes(head));

        System.out.println("tree height: " + treeHeight(new Node(1)));
    }
}
