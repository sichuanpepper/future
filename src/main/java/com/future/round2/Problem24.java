package com.future.round2;

import com.future.utils.DisplayUtils;
import com.future.utils.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.

 For example,
 Given 1->2->3->4, you should return the list as 2->1->4->3.

 Your algorithm should use only constant space. You may not modify the values in the list,
 only nodes itself can be changed.

 * Created by someone on 10/25/17.
 */
public class Problem24 {
    /**
     * Analyze:
     * 1. If there's only one node, return it.
     * 2. If there are two nodes, swap first one and second one.
     * 3. If there are three nodes, do second step and then do first step.
     * 4. ...
     * 5. So recursive is applied.
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        ListNode newHead = head.next.next;
        next.next = head;
        head.next = swapPairs(newHead);
        return next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        DisplayUtils.printListNode(swapPairs(head));
    }
}
