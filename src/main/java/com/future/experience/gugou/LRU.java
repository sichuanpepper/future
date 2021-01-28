package com.future.experience.gugou;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Key-Value store
 * Capacity
 * set(key, value)
 *  - if key exists, overwrite the value, and move it to head.
 *  - if passed the capacity, evict one from tail.
 * get(key)
 *  - if key doesn't exist, return null
 *  - else return the value and move it to head.
 *
 * Operations under set and get
 * - Search key -> map
 * - Move it to head, delete and add -> Doubly LinkedList
 *
 *
 */
public class LRU {
    private class KeyValue {
        public int key;
        public int val;

        public KeyValue(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;

    private Map<Integer, KeyValue> map = new HashMap<>();

    private LinkedList<KeyValue> linkedList = new LinkedList<>();

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        KeyValue obj = map.get(key);
        linkedList.remove(obj);
        linkedList.addFirst(obj);
        return obj.val;
    }

    //in put method, if the key already existed, just need to update value, don't need to check the capacity or evict.
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            KeyValue obj = map.get(key);
            obj.val = value;
            linkedList.remove(obj);
            linkedList.addFirst(obj);
        } else {
            if(map.size() >= capacity) {
                KeyValue obj = linkedList.removeLast();
                map.remove(obj.key);
            }
            KeyValue obj = new KeyValue(key, value);
            map.put(key, obj);
            linkedList.addFirst(obj);
        }

    }

}
