package com.future.round2;

import com.future.utils.ListNode;

/**
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

 For example,
 Given 1->2->3->3->4->4->5, return 1->2->5.
 Given 1->1->1->2->3, return 2->3.

 * Created by xingfeiy on 12/22/17.
 */
public class Problem82 {
    /**
     * Analyze:
     *  - head is null or head.next is null, return head
     *  - 1->1, return null
     *  - 1->1->1, return null
     *  - 1->1->2->2, return null.
     *  - 1->2->2, return 1
     *  - 1->1->2, return 2
     *
     *  Two pointers:
     *  distinct_pointer, always points the last distinct node.
     *  traversal_pointer, keep traversing nodes.
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode preNode = dummy;
        ListNode curNode = head;
        while (curNode != null) {
            if(curNode.next != null && curNode.val == curNode.next.val) {
                //find the next different node or null.
                ListNode tmp = curNode.next;
                while (tmp != null && tmp.val == curNode.val) tmp = tmp.next;
                curNode = tmp;
            } else { //it's an unique.
                preNode.next = curNode;
                preNode = preNode.next;
                curNode = curNode.next;
            }
        }
        preNode.next = null;
        return dummy.next;
    }
}
