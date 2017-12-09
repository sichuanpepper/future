package com.future.round2;

import com.future.utils.ListNode;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.

 For example,

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.
 * Created by someone on 12/08/17.
 */
public class Problem19 {
    /**
     * Analyze:
     * It's single linked list, can't retrieve elements back forward.
     *
     * If we move steps n each time,
     *  - if reached the last element on last step, the deleting element is the start element of last step.
     *  - if last step exceeded the element, move the step back forward element by element until the last step reached the last element.
     *
     *  Example:
     *  1->2->3->4->5  n=2
     *  p1    p2
     *    p1    p2
     *       p1    p2
     *          p1    p2
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head;
        ListNode right = head;
        while (n > 0) {
            right = right.next;
            n--;
        }
        if(right == null) return left.next;

        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return head;
    }
}
