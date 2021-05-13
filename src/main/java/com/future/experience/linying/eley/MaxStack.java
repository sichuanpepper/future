package com.future.experience.linying.eley;

import java.util.PriorityQueue;

/**
 * Always ask the frequency of each APIs if there are multiple APIs.
 *
 * Solution 1:
 * Use two stack (a, b) here, a store pushed number, b store the max number so far.
 *  - push num -> a.push(num), b.push(max(num, b.peek))
 *  - peek -> a.peek
 *  - pop -> a.pop, b.pop
 *  - peekMax -> b.peek
 *  - popMax -> b.pop, keep popping number from a and store it to a tmp stack util get same number as the one popped from b.
 *      - push numbers in tmp stack via push function.
 *      - TC: O(n)
 *
 * Solution 2:
 * Similar as LRU, for the standard method, it's ok to use either ArrayList or LinkedList.
 * But if we want to support popMax, that means we may remove element at any position, then LinkedList is better.
 * And we can store the nodes into a priorityqueue.
 * In this way we can improve the performance
 */
public class MaxStack {
    /**
     * A stacklike data structure that also allows stacklike access
     * to its elements by their value.
     * For example, given a stack of {1, 3, 2, 5, 3, 4, 5, 2}
     * peek() -> 2, peekMax() -> 5
     * pop() -> 2; peek() -> 5, peekMax() -> 5
     * pop() -> 5; peek() -> 4, peekMax() -> 5
     * push(6); peek() -> 6, peekMax() -> 6
     * popMax() -> 6; peek -> 4, peekMax() -> 5
     * popMax() -> 5; peek -> 4, peekMax() -> 4
     */

    private class DoublyLinkedList{
        public int val;
        public DoublyLinkedList pre;
        public DoublyLinkedList next;
        public DoublyLinkedList(int val) {
            this.val = val;
        }
    }

    private DoublyLinkedList header = new DoublyLinkedList(0);
    private DoublyLinkedList tail = new DoublyLinkedList(0);
    private PriorityQueue<DoublyLinkedList> pq;

    public MaxStack() {
        header.next = tail;
        tail.pre = header;
        pq = new PriorityQueue<>((a, b) -> Integer.compare(b.val, a.val));

    }

    // The standard three Stack methods:

    /** Add an element to the stack. */
    public void push(int toPush) {
        DoublyLinkedList node = new DoublyLinkedList(toPush);
        node.pre = tail.pre;
        node.next = tail;
        tail.pre.next = node;
        tail.pre = node;
        pq.offer(node);
    }

    /** Return the top value on the stack. */
    public int peek(){
        return tail.pre.val;
    }

    /** Remove and return the top value from the stack. */
    public int pop(){
        DoublyLinkedList node = tail.pre;
        tail.pre = node.pre;
        node.pre.next = tail;
        node.pre = null;
        node.next = null;
        return node.val;
    }

    // Two special methods, so this isn't just 'implement a stack':

    /** Return the largest value in the stack. (Remember that T must implement Comparable.) */
    public int peekMax() {
        return pq.peek().val;
    }

    /** Remove and return the largest value from the stack. */
    public int popMax(){
        DoublyLinkedList node = pq.poll();
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
        return node.val;
    }


    public static void main(String[] args) {
        MaxStack ms = new MaxStack();
        //{1, 3, 2, 5, 3, 4, 5, 2}
        ms.push(1);
        ms.push(3);
        ms.push(2);
        ms.push(5);
        ms.push(3);
        ms.push(4);
        ms.push(5);
        ms.push(2);
        System.out.println(ms.peek());
        System.out.println(ms.peekMax());
        System.out.println(ms.peek());
        System.out.println(ms.popMax());
        System.out.println(ms.peek());

    }
}
