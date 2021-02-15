package com.future.experience.linying;

/**
 * https://leetcode.jp/problemdetail.php?id=1650
 *
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is
 * the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 */
public class LCA3 {
    class PNode {
        public int val;
        public PNode left;
        public PNode right;
        public PNode parent;
    }

    /**
     * It sounds like a tree problem, but actually it's linkedlist problem.
     * Find the intersection of two linkedlist.
     * https://leetcode.com/problems/intersection-of-two-linked-lists/
     *
     * @param p
     * @param q
     * @return
     */
    public PNode lca(PNode p, PNode q) {
        return null;
    }
}
