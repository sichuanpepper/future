package com.future.round2;

import com.future.utils.ListNode;
import com.future.utils.RandomListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 A linked list is given such that each node contains an additional random pointer which could point to any
 node in the list or null.

 Return a deep copy of the list.

 * Created by xingfeiy on 4/4/18.
 */
public class Problem138 {
    /**
     * How to copy a linked list
     * @param head
     * @return
     */
    public ListNode copyLinkedList(ListNode head) {
        if(head == null) {
            return null;
        }

        ListNode oh = head, ch = null, pre = null;
        while (oh != null) {
            ListNode cn = new ListNode(oh.val);
            if(ch == null) {
                ch = cn;
            }
            if(pre != null) {
                pre.next = cn;
            }
            pre = cn;
            oh = oh.next;
        }
        return ch;
    }

    /**
     * Analyze:
     * It's easy to copy an ordinary linked list, but this list node contains a random pointer which points a node may
     * created or un-created.
     *
     * So the straightforward ways is clone all nodes without pointer first, and then create the pointer.
     *
     * Time complexity O(n) and space complexity O(n), beats 50%
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        //key -> original node, value -> clone
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode node = head;
        //clone all node without pointers.
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        //create pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }

    /**
     * Analyze:
     * Improve it based on map solution, why we need map? Because we have to keep the mapping between original and clone.
     * Here, we use another way to keep this relationship without extra space. Example:
     *
     * 1->3->4->5->null
     * 1->1'->3->3'->4->4'->5->5'->null
     *
     * then, just like what we did in map solution to create the pointers.
     *
     * Time complexity O(n), space complexity O(1), beats 99%
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomListV2(RandomListNode head) {
        if(head == null) return null;
        RandomListNode node = head;
        while (node != null) {
            RandomListNode clone = new RandomListNode(node.label);
            clone.next = node.next;
            node.next = clone;
            node = clone.next;
        }

        //create random pointer
        node = head;
        while (node != null) {
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }

        node = head;
        RandomListNode newHead = head.next;
        while (node != null) {
            RandomListNode clone = node.next;
            node.next = clone.next;
            if(node.next != null) clone.next = node.next.next;
            node = node.next;
        }
        return newHead;
    }



    public static void main(String[] args) {
        Problem138 p = new Problem138();
        RandomListNode head = new RandomListNode(-1);
        p.copyRandomList(head);
    }
}
