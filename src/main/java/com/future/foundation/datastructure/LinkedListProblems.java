package com.future.foundation.datastructure;

import com.future.utils.DisplayUtils;
import com.future.utils.ListNode;

/**
 * Created by xingfeiy on 4/3/18.
 */
public class LinkedListProblems {

    /**
     * Reverse a link list
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        while (head != null) {
            ListNode tmp = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = tmp;
        }
        return dummy.next;
    }

    /**
     * Find the middle node in the link.
     * If the length of link is even, the mid = length / 2, otherwise, mid = length / 2 + 1,
     * except length == 1 and length == 2
     * @param head
     * @return
     */
    public static ListNode findMid(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * Merge two sorted links.
     * @param first
     * @param second
     * @return
     */
    public static ListNode mergeSortedLinks(ListNode first, ListNode second) {
        if(first == null) return second;
        if(second == null) return first;
        if(first.val < second.val) {
            first.next = mergeSortedLinks(first.next, second);
            return first;
        }
        second.next = mergeSortedLinks(first, second.next);
        return second;
    }

    /**
     * There are 3 popular sort algorithms for array, quick sort, merge sort, heap sort.
     *
     * Here we use merge sort.
     * @param head
     * @return
     */
    public static ListNode sort(ListNode head) {
        if(head == null || head.next == null) return head;
        //Here we need to find the middle node, but we can't use the method likes findMid above.
        //Because if there are two nodes, the middle always points second node.
        ListNode slow = head, fast = head, preEnd = head;
        while (fast != null && fast.next != null) {
            preEnd = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        preEnd.next = null;
        ListNode left = sort(head);
        ListNode right = sort(slow);

        return mergeSortedLinks(left, right);
    }

    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }

    /**
     * If cycle is available:
     *       h-------s--------
     *               |        |
     *               |        |
     *               |        |
     *               ----m----
     *
     *  h: the head of link
     *  s: the start node of cycle.
     *  m: the node where slower met faster.
     *  l: the length of link.
     *  c: the length of cycle.
     *  And we can got following formulas:
     *  2*(hs + sm) = hs + sm + N*c (N means the number of cycles faster has been passed)
     *   -> hs + sm = N*c -> hs + sm = (N - 1)*c + c
     *  c = sm + ms (the length of cycle)
     *   -> hs + sm = (N - 1)*c + sm + ms
     *   -> hs = (N - 1)*c + ms, here (N - 1)*c actually can get ignore.
     * @param head
     * @return
     */
    public static ListNode findStartOfCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) { // meeting on node m
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * Find the kth node from end of link.
     * ex, 1->2->3->4->5, k=2, return 4.
     *
     * Analyze:
     * h----->k-->e
     * h: the head of link
     * k: kth node from end
     * e: the end of link
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode findKthNodeFromEnd(ListNode head, int k) {
        if(head == null || k < 0) return null;
        ListNode slow = head, fast = head;
        while (fast != null && k-- > 1) fast = fast.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * Delete kth node from end, and then return the head of new link.
     *
     * Test case:
     * 1. 1->null, k=1
     * 2. 1->2->null, k=3
     * @param head
     * @param k
     * @return
     */
    public static ListNode deleteKthNodeFromEnd(ListNode head, int k) {
        if(head == null || k < -1) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        while (fast!= null && k-- > 0) fast = fast.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     *
     * first:  1->2->3->4
     *                  ->5->6->null
     * second:       0->0
     *
     * returns: 5->6->null
     *
     * Analyze:
     * l1: the length of first link
     * l2: the length of second link
     * l1 + l2 = l2 + l1, which means one pointer traversal from fist to end then from second to first, other pointer
     * from second to end then from fist to end. If there are intersection, two pointers will finish these intersection together.
     *
     * if there's no intersection at all, then the null could be the intersection, two pointers will arrive null at same time.
     *
     * @param first
     * @param second
     * @return
     */
    public static ListNode findIntersection(ListNode first, ListNode second) {
        if(first == null || second == null) return null;
        ListNode pointer1 = first, pointer2 = second;
        while (pointer1 != pointer2) {
            pointer1 = pointer1 == null ? second : pointer1.next;
            pointer2 = pointer2 == null ? first : pointer2.next;
        }
        return pointer1;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);

        ListNode head2 = null;

        ListNode head3 = new ListNode(1);

        DisplayUtils.printListNode(head1);
        DisplayUtils.printListNode(reverse(head1));

        DisplayUtils.printListNode(head2);
        DisplayUtils.printListNode(reverse(head2));

        DisplayUtils.printListNode(head3);
        DisplayUtils.printListNode(reverse(head3));

        System.out.println("Finding middle node");
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);

        DisplayUtils.printListNode(head1);
        System.out.println(findMid(head1).val);


        System.out.println("Sorting.....");
        ListNode head = new ListNode(4);
        head.next = new ListNode(6);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        DisplayUtils.printListNode(sort(head));
    }
}
