package com.future.generic;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Thoughts:
 * There are three ranges, the leading range, reverse range, tail range
 * - leading range and tail range can be empty, reverse range contains at least one node.
 * - keys nodes: the head and tail of leading range, head of tail range
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        //find tail of leading range
        ListNode tailOfLR = dummy;
        for(int i = 1; i < left; i++) {
            tailOfLR = tailOfLR.next;
        }

        //reverse left - right
        ListNode tailOfRR = tailOfLR.next, p = tailOfLR.next; //tail of reverse range
        for(int i = left; i <= right; i++) {
            ListNode next = p.next;
            p.next = tailOfLR.next;
            tailOfLR.next = p;
            p = next;
        }

        tailOfRR.next = p;

        return dummy.next;
    }
}
