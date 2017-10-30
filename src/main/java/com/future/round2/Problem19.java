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
 * Created by someone on 10/11/17.
 */
public class Problem19 {
    /**
     * The given n will always be valid, it's very important point, which means, the length of linked list >= n.
     * and we can know, the remove one must be in the last full n-segment.
     * likes, 1, 2, 3, 4, 5, and delete 2nd, then the last full segment is (3, 4),
     * likes, 1, 2, 3, 4, 5, and delete 3rd, then the last full segment is (1, 2, 3)
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for(int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        //todo
        return null;
    }
}
