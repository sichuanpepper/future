package com.future.round2;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 *
 Given a sorted linked list, delete all duplicates such that each element appear only once.

 For example,
 Given 1->1->2, return 1->2.
 Given 1->1->2->3->3, return 1->2->3.

 * Created by xingfeiy on 12/11/17.
 */
public class Problem83 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode node1 = head;
        ListNode node2 = head.next;
        while (node2 != null) {
            if(node2.val != node1.val) {
                node1.next.val = node2.val;
                node1 = node1.next;
            }
            node2 = node2.next;
        }
        node1.next = null;
        return head;
    }
}
