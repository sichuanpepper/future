package com.future.foundation.datastructure.hashmap;

/**
 * Hash map(hash table) is a data structure with key-value pair, which offers constant lookups.
 *
 * A hash map has a hash function to compute an index into array of slots, from which the desired value can be found.
 *
 * Ideally, a hash function will assign each key to a unique slots, we called perfect hash function, no collisions.
 *
 * The performance of an implementation of hash map depends on the hash function, so what's is a good hash function?
 *  - Uniform distribution hash values.
 *
 * Created by xingfeiy on 3/22/18.
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

        while (true) {
            if(entry.hashcode == key.hashCode()) {
                V oldVal = entry.value;
                entry.value = value;
                return oldVal;
            }
            if(entry.next == null) break;
            entry = entry.next;
        }

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
