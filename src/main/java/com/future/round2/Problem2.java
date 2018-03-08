package com.future.round2;

import com.future.utils.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.

   You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 * Created by someone on 10/10/17.
 */
public class Problem2 {
    /**
     * Analyze:
     * Step1: calculate the sum from left to right.
     * Step2: check the sum and set the carry.
     * Step3: Set result.
     * Step4: move pointers.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //Assume both l1 and l2 are valid.
        ListNode pointer1 = l1;
        ListNode pointer2 = l2;
        ListNode res = null;
        ListNode resPointer = null;
        boolean hasCarry = false;
        // step1: while loop conditions, either pointer1 or pointer2 is not null or has carry.
        while (pointer1 != null || pointer2 != null || hasCarry) {
            //calculate sum
            int sum = (pointer1 == null ? 0 : pointer1.val) + (pointer2 == null ? 0 : pointer2.val) + (hasCarry ? 1 : 0);

            //check sum.
            hasCarry = sum > 9;
            if(sum > 9) {
                sum = sum - 10;
            }

            //set result;
            if(res == null) {
                resPointer = res = new ListNode(sum);
            } else {
                resPointer.next = new ListNode(sum);
                resPointer = resPointer.next;
            }

            //move pointers
            pointer1 = (pointer1 == null) ? null : pointer1.next;
            pointer2 = (pointer2 == null) ? null : pointer2.next;
        }
        return res;
    }

    /**
     * 02/01/2018
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_v2(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return null;
        ListNode res = new ListNode(0);
        ListNode cur = res;
        int carry = 0;
        while(l1 != null || l2 != null || carry > 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;
            cur.next = new ListNode(sum);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return res.next;
    }


    public static void main(String[] args) {}
}
