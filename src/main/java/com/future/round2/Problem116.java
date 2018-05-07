package com.future.round2;

import com.future.utils.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 *
 * Created by xingfeiy on 5/6/18.
 */
public class Problem116 {
    /**
     * The BFS way
     * time complexity: O(n)
     * space complexity: O(w) the width of tree
     * @param root
     */
    public void bfs(TreeLinkNode root) {
        if(root == null) return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode pre = null;
            for(int i = 0; i < size; i++) {
                TreeLinkNode tmp = queue.poll();
                if(tmp.left != null) queue.offer(tmp.left);
                if(tmp.right != null) queue.offer(tmp.right);
                if(pre != null) pre.next = tmp;
                pre = tmp;
            }
        }
    }

    /**
     * Iterate way beats 78%
     * time complexity: O(n)
     * space complexity: O(1)
     * @param root
     */
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode head = root;
        while(head != null) {
            TreeLinkNode t = head;
            TreeLinkNode pre = null;
            while(t != null) {
                if(t.left != null) {
                    if(pre != null) pre.next = t.left;
                    pre = t.right;
                    t.left.next = t.right;
                }
                t = t.next;
            }
            head = head.left;
        }
    }

    /**
     * Recursive way beats 100%
     *
     * @param root
     */
    public void recursive(TreeLinkNode root) {
        if(root == null) return;
        if(root.left != null) {
            root.left.next = root.right;
            if(root.next != null) root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }
}
