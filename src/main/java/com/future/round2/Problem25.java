package com.future.round2;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 k is a positive integer and is less than or equal to the length of the linked list.
 If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5
 * Created by someone on 10/25/17.
 */
public class Problem25 {
    /**
     * Analyze:
     * 1. It's similar with problem 24
     * 2. How to reverse linked list with length k?
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k < 2) return head;
        int length = 0;
        ListNode nextHead = head;
        while(nextHead != null && length != k) {
            nextHead = nextHead.next;
            length++;
        }

        if(length == k) {
            nextHead = reverseKGroup(nextHead, k);
            ListNode dummy = new ListNode(0);
            ListNode tail = head;
            while (length-- > 0) {
                ListNode tmp = head.next;
                head.next = dummy.next;
                dummy.next = head;
                head = tmp;
            }
            tail.next = nextHead;
            return dummy.next;

        }

        return head;
    }
}
