package com.future.foundation.tree;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xingfeiy on 6/3/17.
 */
public class BinarySearchTree {
    public static class Node {
        public int val;

        public Node left = null;

        public Node right = null;
    }

    public static void printInorder(Node tree) {
        if(tree == null) {
            return;
        }

        printInorder(tree.left);
        System.out.print(tree.val + " ");
        printInorder(tree.right);
    }

    public static int[] outInorder(Node tree) {
        List<Integer> list = outInorder(tree, new ArrayList<>());
        int[] array = new int[list.size()];
        int index = 0;
        for(int val : list) {
            array[index++] = val;
        }
        return array;
    }

    public static List<Integer> outInorder(Node tree, List<Integer> array) {
        if(tree == null) {
            return array;
        }

        outInorder(tree.left, array);
        array.add(tree.val);
        outInorder(tree.right, array);
        return array;
    }

    public static Node search(Node tree, int value) {
        if(tree == null) {
            return null;
        }

        if(tree.val == value) {
            return tree;
        }

        if(value < tree.val) {
            return search(tree.left, value);
        }
        return search(tree.right, value);
    }

    public static Node searchV2(Node tree, int value) {
        if(tree == null) {
            return null;
        }

        while (tree != null) {
            if(tree.val == value) {
                return tree;
            } else if(tree.val > value) {
                tree = tree.left;
            } else {
                tree = tree.right;
            }
        }
        return null;
    }

    public static int minimum(Node tree) {
        if(tree == null) {
            return Integer.MAX_VALUE;
        }

        while (tree.left != null) {
            tree = tree.left;
        }
        return tree.val;
    }

    public static int maximum(Node tree) {
        if(tree == null) {
            return Integer.MIN_VALUE;
        }
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree.val;
    }

    public static int successor(Node tree, int value) {
        if(tree == null || tree.right == null) {
            return Integer.MAX_VALUE;
        }

        return minimum(tree.right);
    }

    public static void insert(Node tree, Node node) {
        if(node == null) {
            return;
        }

        Node parent = null;
        Node tmpNode = tree;
        while (tmpNode != null) {
            parent = tmpNode;
            if(node.val <= tmpNode.val) {
                tmpNode = tmpNode.left;
            } else {
                tmpNode = tmpNode.right;
            }
        }

        if(parent == null) {
            tree = node;
        } else {
            if(node.val <= parent.val) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
    }

    public static void printDFS(Node node, List<Integer> cur) {
        if(node == null) {
            return;
        }

        cur.add(node.val);
        //if it's leaf, print it.
        if(node.left == null && node.right == null) {
            DisplayUtils.printList(cur);
            return;
        }

        printDFS(node.left, cur);
        cur.remove(cur.size() - 1);
        printDFS(node.right, cur);
        cur.remove(cur.size() - 1);

    }

    public static List<Integer> getLeafs(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        getLeafs(root, res);
        return res;
    }

    private static void getLeafs(Node node, List<Integer> res) {
        if(node == null) {
            return;
        }

        if(node.left == null && node.right == null) {
            res.add(node.val);
            return;
        }

        getLeafs(node.left, res);
        getLeafs(node.right, res);
    }

    public static List<List<Integer>> getAllPaths(Node root) {
        if(root == null) {
            return Collections.EMPTY_LIST;
        }

        List<List<Integer>> res = new ArrayList<>();
        getAllPaths(root, res, new ArrayList<>());
        return res;
    }

    private static boolean isLeaf(Node node) {
        return node == null ? false : (node.left == null && node.right == null);
    }
    public static void getAllPaths(Node node, List<List<Integer>> res, List<Integer> cur) {
        //think about should we check if node equals null or if node is a leaf????
        if(node == null) {
            return;
        }

        cur.add(node.val);
        if(node.left == null && node.right == null) {
            res.add(new ArrayList<>(cur));

            //why can't remove the last element here?????
            //if we remove last element here, we can remove the leafs only, but we still need keep remove last element when we rollback.
//            cur.remove(cur.size() - 1);
        }

//        getAllPaths(node.left, res, cur);
//        getAllPaths(node.right, res, cur);

        //if we construct a new array here, then we don't need remove last one element in the last of this method.
        //but the space will be wasted.
        getAllPaths(node.left, res, new ArrayList<>(cur));
        getAllPaths(node.right, res, new ArrayList<>(cur));

        //remove last element in right place.
//        cur.remove(cur.size() - 1);
    }

    private static void getAllLeftPaths(Node node, List<List<Integer>> res, List<Integer> cur) {
        if(node == null) {
            return;
        }

        //Can we add the value of node into cur directly?
        // We can't since the node might be right leaf.
        //But how can we know if this node is a right leaf or not or something else? there's no way to know that in this method.
        //One way is we add one more parameter to indicate the node is left or right.
        //Other way is we check if the left node is leaf or not.
        cur.add(node.val);

        if(isLeaf(node.left)) {
            cur.add(node.left.val);
            res.add(new ArrayList<>(cur));
            return;
        }

        getAllLeftPaths(node.left, res, cur);
        getAllLeftPaths(node.right, res, cur);

    }

//    public static List<List<Integer>> getAllLeftPaths(Node root) {}

    public static void main(String[] args) {
        Node head = new Node();
        head.val = 5;

        Node left1 = new Node();
        left1.val = 3;
        head.left = left1;

        Node right1 = new Node();
        right1.val = 7;
        head.right = right1;

        Node left11 = new Node();
        left11.val = 2;
        left1.left = left11;

        Node left12 = new Node();
        left12.val = 5;
        left1.right = left12;

        Node right12 = new Node();
        right12.val = 10;
        right1.right = right12;

        Node right11 = new Node();
        right11.val = 6;
        right1.left = right11;

//        printInorder(head);
//
//        System.out.println();
//        DisplayUtils.printArray(outInorder(head));
//
//        System.out.println("Now, find the minimum value in the tree");
//        System.out.println(minimum(head));
//
//        System.out.println("Now, find the maximum value in the tree");
//        System.out.println(maximum(head));
//
//        System.out.println("Now, search a node in the tree");
//        System.out.println(search(head, 7).val);
//        System.out.println(search(head, 9));
//
//        System.out.println("Now, search a node in the tree");
//        System.out.println(searchV2(head, 7).val);
//        System.out.println(searchV2(head, 9));
//
//        System.out.println("Now, find the successor node.");
//        System.out.println(successor(head, 5));


        System.out.println("==========");
        printDFS(head, new ArrayList<>());

        System.out.println("==========");
        DisplayUtils.printList(getLeafs(head));

        System.out.println("========== get  all paths");
        for(List<Integer> path : getAllPaths(head)) {
            DisplayUtils.printList(path);
        }
    }
}
