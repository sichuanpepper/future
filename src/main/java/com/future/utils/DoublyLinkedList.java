package com.future.utils;

/**
 * Created by xingfeiy on 5/9/18.
 */
public class DoublyLinkedList {
    public int val;
    public DoublyLinkedList pre;
    public DoublyLinkedList next;

    public DoublyLinkedList(int val) {
        this.val = val;
    }

    public static void display(DoublyLinkedList head) {
        if(head == null) return;
        DoublyLinkedList cur = head;
        do {
            System.out.print(cur.val + "<=>");
            cur = cur.next;
        } while (cur != null && cur != head);
        System.out.println();
    }

    public static DoublyLinkedList buildDLL(int[] nums) {
        if(nums == null || nums.length < 1) return null;
        DoublyLinkedList head = new DoublyLinkedList(nums[0]);
        DoublyLinkedList pre = head;
        for(int i = 1; i < nums.length; i++) {
            DoublyLinkedList cur = new DoublyLinkedList(nums[i]);
            pre.next = cur;
            cur.pre = pre;
            pre = cur;
        }
        if(pre != head) {
            pre.next = head;
            head.pre = pre;
        }
        return head;
    }

    public static void main(String[] args) {
        display(buildDLL(new int[]{1, 2, 3, 4}));
        display(buildDLL(new int[]{1}));
    }
}
