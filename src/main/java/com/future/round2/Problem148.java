package com.future.round2;

import com.future.utils.DisplayUtils;
import com.future.utils.ListNode;

/**
 *https://leetcode.com/problems/sort-list/description/
 *
 Sort a linked list in O(n log n) time using constant space complexity.

 * Created by xingfeiy on 12/29/17.
 */
public class Problem148 {
    /**
     * Analyze:
     * Both quick sort and merge sort can sort an array in O(n log n) time
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
//        sortList(head, null);
        if(head == null || head.next == null) return head;

        //find middle point

        ListNode slow = head;
        ListNode preEnd = slow;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            preEnd = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        preEnd.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        head = merge(left, right);
        return head;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode curNode = dummy;
        while (left != null || right != null) {
            if(left == null) {
                curNode.next = right;
                right = right.next;
            } else if(right == null) {
                curNode.next = left;
                left = left.next;
            } else {
                if(left.val < right.val) {
                    curNode.next = left;
                    left = left.next;
                } else {
                    curNode.next = right;
                    right = right.next;
                }
            }
            curNode = curNode.next;
        }
        return dummy.next;
    }

//    private void sortList(ListNode start, ListNode end) {
//        if(start == end) return;
//        ListNode partial = partition(start, end);
//        sortList(start, partial);
//        sortList(partial, end);
//    }
//
//    private ListNode partition(ListNode start, ListNode end) {
//        int pivot = start.val;
//        ListNode travel = start.next;
//        ListNode pointer = start;
//        while (travel != end) {
//            if(travel.val < pivot) {
//                pointer = pointer.next;
//                int tmp = pointer.val;
//                pointer.val = travel.val;
//                travel.val = tmp;
//            }
//            travel = travel.next;
//        }
//        start.val = pointer.val;
//        pointer.val = pivot;
//        return pointer;
//    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        Problem148 p = new Problem148();
        DisplayUtils.printListNode(p.sortList(head));
    }
}
