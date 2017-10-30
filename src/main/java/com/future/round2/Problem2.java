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


    public static void main(String[] args) {}
}
