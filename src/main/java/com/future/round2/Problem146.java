package com.future.round2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/description/
 *
 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
 * Created by xingfeiy on 1/3/18.
 *
 *
 * Analyze:
 * - We need a collection to store values.
 * - put operation, that insertion, LinkedList in mind
 * - get operation, lookup and move(delete && insert), double linked list in mind.
 * - Eviction
 * - LinkedList is not good for quick lookup, so we can use extra data structure, likes HashMap.
 */
public class Problem146{
    private class DoubleLinkedList{
        public int key;
        public int val;
        DoubleLinkedList pre;
        DoubleLinkedList next;
        public DoubleLinkedList(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }

    private int capacity;

    private DoubleLinkedList dummyHead = new DoubleLinkedList(0, 0);

    private DoubleLinkedList dummyTail = new DoubleLinkedList(0, 0);

    private Map<Integer, DoubleLinkedList> map = new HashMap<>();


    public Problem146(int capacity) {
        this.capacity = capacity;
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        int val = map.get(key).val;
        moveToHead(map.get(key));
        return val;
    }

    public void put(int key, int value) {
        if(this.capacity == 0) {
            removeTail();
        }
        DoubleLinkedList node;
        if(map.containsKey(key)) {
            node = map.get(key);
            node.val = value;
            moveToHead(node);
        } else {
            node = new DoubleLinkedList(key, value);
            addToHead(node);
            this.capacity--;
        }

        map.put(key, node);

    }

    private void addToHead(DoubleLinkedList node) {
        dummyHead.next.pre = node;
        node.next = dummyHead.next;
        node.pre = dummyHead;
        dummyHead.next = node;
    }

    private void moveToHead(DoubleLinkedList node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        dummyHead.next.pre = node;
        node.next = dummyHead.next;
        node.pre = dummyHead;
        dummyHead.next = node;
    }


    private void removeTail() {
        if(dummyTail.pre == dummyHead) return;
        map.remove(dummyTail.pre.key);
        dummyTail.pre.pre.next = dummyTail;
        dummyTail.pre = dummyTail.pre.pre;

        this.capacity++;
    }


    public static void main(String[] args) {
        Problem146 cache = new Problem146(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

}
