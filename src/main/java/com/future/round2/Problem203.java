package com.future.round2;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/description/
 * Remove all elements from a linked list of integers that have value val.

 Example
 Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 Return: 1 --> 2 --> 3 --> 4 --> 5

 * Created by xingfeiy on 3/8/18.
 */
public class Problem203 {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tmp = dummy;
        while(tmp != null) {
            if(tmp.next != null && tmp.next.val == val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return dummy.next;
    }

    /**
     * use recursive solution
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementsV2(ListNode head, int val) {
        if(head == null) return null;
        head.next = removeElementsV2(head.next, val);
        return head.val == val ? head.next : head;
    }


}
