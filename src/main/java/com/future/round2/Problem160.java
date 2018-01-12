package com.future.round2;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 Write a program to find the node at which the intersection of two singly linked lists begins.


 For example, the following two linked lists:

 A:       a1 → a2
                 ↘
                 c1 → c2 → c3
                 ↗
 B:   b1 → b2 → b3
 begin to intersect at node c1.


 Notes:

 If the two linked lists have no intersection at all, return null.
 The linked lists must retain their original structure after the function returns.
 You may assume there are no cycles anywhere in the entire linked structure.
 Your code should preferably run in O(n) time and use only O(1) memory.

 * Created by xingfeiy on 1/3/18.
 */
public class Problem160 {
    /**
     * Analyze:
     * The path from a1 to c3 may equal or may not equal to path from b1 to c3, we can't know that.
     * But, think about two people, one people start from a1, and other people start from b1, and once they reached c3, then
     * go back and then from the other entrance and continue heading to c3, they must meet at c1 and finish the rest path together.
     * Here, c1 could be null if there's no intersection.
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }
}
