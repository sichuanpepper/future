package com.future.round2;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list/description/
 *
 * Reverse a singly linked list.
 *
 * Created by xingfeiy on 12/10/17.
 */
public class Problem206 {
    /**
     * Analyze:
     * dummy->1->2->3, dummy->2->1->3, dummy->3->2->1
     * As above example, tmp = cur.next; cur.next = cur.next.next;tmp.next = dummy.next; dummy.next = tmp;
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = tmp;
        }

        return dummy.next;
    }

    public ListNode solution2(ListNode head) {
        ListNode preNode = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = preNode;
            preNode = head;
            head = nextNode;
        }

        return preNode;
    }

    public static void main(String[] args) {
        Problem206 p = new Problem206();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        System.out.println(p.reverseList(head).val);
        System.out.println(p.reverseList(head).next.val);
    }
}
