package com.future.foundation.datastructure;

import com.future.utils.DisplayUtils;
import com.future.utils.ListNode;

/**
 * Created by xingfeiy on 4/3/18.
 */
public class MyLinkedList {
    public static ListNode sort(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode end = head, mid = head, preEnd = null;
        while (end != null && end.next != null) {
            preEnd = mid;
            mid = mid.next;
            end = end.next.next;
        }
        preEnd.next = null;

        ListNode left = sort(head);
        ListNode right = sort(mid);
        return merge(left, right);
    }


    private static ListNode merge(ListNode first, ListNode second) {
        if(first == null) return second;
        if(second == null) return first;
        if(first.val < second.val) {
            first.next = merge(first.next, second);
            return first;
        }
        second.next = merge(first, second.next);
        return second;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(1);
        DisplayUtils.printListNode(sort(head));
    }
}
