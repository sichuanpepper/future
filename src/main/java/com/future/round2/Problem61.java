package com.future.round2;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/rotate-list/description/
 *
 Given a list, rotate the list to the right by k places, where k is non-negative.


 Example:

 Given 1->2->3->4->5->NULL and k = 2,

 return 4->5->1->2->3->NULL.

 * Created by xingfeiy on 12/10/17.
 */
public class Problem61 {
    /**
     * Analyze:
     * It's similar with Problem 19, remove nth node from end of list.
     *
     * The first thing is understand what is rotate? The question is asking rotate to right, that means clockwise rotate
     * 1->2->3->4->5, 5->1->2->3->4, 4->5->1->2->3
     *
     * How about k = 0 or k is larger than length of list?
     * k = 0, return the original list.
     * k > length of list, likes
     * 1->2 and k = 3
     * 1->2, 2->1 (k=1), 1->2 (k=2), 2->1 (k=3)
     * As we can see, when k equals to length of list, the rotated list is same as original array.
     * So that means we just need rotate k % length.
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k < 1) return head;

        //find length
        int length = 1;
        ListNode counter = head;
        while (counter.next != null) {
            counter = counter.next;
            length++;
        }

        k = k % length;
        if(k < 1) return head;

        //here we already know the length of list, so we don't have to do same thing as we did in problem 19.
        ListNode p1 = head;
        ListNode p2 = counter;
        k = length - k - 1;
        while (k > 0) {
            p1 = p1.next;
            k--;
        }

        p2.next = head;
        head = p1.next;
        p1.next = null;
        return head;
    }
}

