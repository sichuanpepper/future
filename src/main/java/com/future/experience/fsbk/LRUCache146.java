package com.future.experience.fsbk;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 */
public class LRUCache146 {
    /**
     get
     1. Lookup
     2. Move it to head
     put
     1. Lookup and overwrite value
     2. Add/Move it to head
     3. Delete tail
     Move it to head
     1. Delete and Add

     HashMap && DoublyLinkedList
     **/

    private class DoublyLinkedList {
        public int val;
        public DoublyLinkedList pre;
        public DoublyLinkedList next;
    }

    //key -> key, value -> node
    private Map<Integer, DoublyLinkedList> map = new HashMap<>();

    private DoublyLinkedList header = new DoublyLinkedList();

    private DoublyLinkedList tail = new DoublyLinkedList();

    private int capacity = 0;

    public LRUCache146(int capacity) {
        this.capacity = capacity;
        header.next = tail;
        tail.pre = header;
    }

    //Lookup, then move it to head
    public int get(int key) {
        if(!exists(key)) return -1;
        DoublyLinkedList node = map.get(key);
        delete(node);
        addToHead(node);
        return node.val;
    }

    private void delete(DoublyLinkedList node) {
        if(node == null) return;
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void addToHead(DoublyLinkedList node) {
        if(node == null) return;
        node.next = header.next;
        node.pre = header;
        header.next.pre = node;
        header.next = node;
    }

    private boolean exists(int key) {
        return map.containsKey(key);
    }

    private void evict() {
        DoublyLinkedList last = this.tail.pre;

        delete(last);

    }

    public void put(int key, int value) {
        if(exists(key)) return;
        if(this.capacity == 0) {
            delete(this.tail.pre);
            this.capacity++;
        }

        DoublyLinkedList node = new DoublyLinkedList();
        node.val = value;
        addToHead(node);
        map.put(key, node);
        this.capacity--;
    }

    public static void main(String[] args) {
        LRUCache146 cache = new LRUCache146( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}
