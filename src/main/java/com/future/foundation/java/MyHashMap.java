package com.future.foundation.java;

/**
 * Created by xingfeiy on 9/27/17.
 */
public class MyHashMap<K, V> {
    private class Entry<K, V> {
        public K key;

        public V value;

        public int hashcode;

        public Entry next;
    }

    private int capacity = 32;

    private Entry<K, V>[] entries = new Entry[this.capacity];

    public MyHashMap() {}

    public V put(K key, V value) {
        int hashIndex = key.hashCode() & entries.length - 1;
        if(entries[hashIndex] == null) {
            Entry<K, V> entry = new Entry<>();
            entry.key = key;
            entry.value = value;
            entry.hashcode = key.hashCode();
            entries[hashIndex] = entry;
            return null;
        }

        Entry<K, V> entry = entries[hashIndex];
        do {
            if(entry.hashcode == key.hashCode()) {
                V oldVal = entry.value;
                entry.value = value;
                return oldVal;
            }
        } while (entry.next != null);

        Entry<K, V> newEntry = new Entry<>();
        newEntry.key = key;
        newEntry.value = value;
        newEntry.hashcode = key.hashCode();
        entry.next = newEntry;
        return null;
    }


    public static void main(String[] args) {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put(3, 5);
        myHashMap.put(4, 5);
        myHashMap.put(5, 5);
        myHashMap.put(3, 5);
    }
}
