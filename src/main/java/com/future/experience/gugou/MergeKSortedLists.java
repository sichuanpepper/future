package com.future.experience.gugou;

import com.future.utils.ListNode;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = new ListNode();
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b)->Integer.compare(a.val, b.val));
        for(ListNode node : lists) {
            if(node != null) {
                queue.offer(node);
            }
        }

        ListNode pointer = res;
        while(!queue.isEmpty()) {
            ListNode node = queue.poll();
            pointer.next = new ListNode(node.val);
            if(node.next != null) {
                queue.offer(node.next);
            }
            pointer = pointer.next;
        }
        return res.next;
    }

}
