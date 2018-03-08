package com.future.round2;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/description/
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

    Note: Do not modify the linked list.

    Follow up:
    Can you solve it without using extra space?
 * Created by xingfeiy on 3/6/18.
 */
public class Problem142 {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head, fast = head, entry = null;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                entry = head;
                while (fast != entry) {
                    fast = fast.next;
                    entry = entry.next;
                }
                return entry;
            }
        }
        return null;
    }
}
