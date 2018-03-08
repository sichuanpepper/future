package com.future.round2;

import com.future.utils.ListNode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/add-two-numbers-ii/description/

 You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Follow up:
 What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

 Example:

 Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 8 -> 0 -> 7

 * Created by xingfeiy on 2/1/18.
 */
public class Problem445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while(l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while(l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode res = new ListNode(0);
        int carry = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = (stack1.isEmpty() ? 0 : stack1.pop()) + (stack2.isEmpty() ? 0 : stack2.pop()) + carry;
            carry = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;
            ListNode tmp = new ListNode(sum);
            tmp.next = res.next;
            res.next = tmp;
        }
        return res.next;
    }
}
