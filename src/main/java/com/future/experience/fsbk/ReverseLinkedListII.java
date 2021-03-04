package com.future.experience.fsbk;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListII {
    /**
     * Think about how do we reverse a full linkedlist?
     * Go through node one by one, for each node, we move it to the head and modify the pointer.
     *  - We need a pointer that always points the head of linkedlist.
     *  -
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i = 1; i < left; i++) {
            pre = pre.next;
        }
        ListNode p = pre.next, tail = pre.next;

        for(int i = left; i <= right; i++) {
            ListNode tmp = p.next;
            p.next = pre.next;
            pre.next = p;
            p = tmp;
        }
        tail.next = p;
        return dummy.next;
    }
}
