package com.future.foundation.datastructure.hashmap;

public class HashMapTrain<K, V> {
    private class Entry<K, V> {
        public K key;
        public V value;
        public int hashcode;
        public Entry next;
    }

    private int capacity = 1 << 32;
    private Entry<K, V>[] entrySet = new Entry[capacity];

    public V put(K key, V value) {
        int hashIndex = value.hashCode() & (capacity - 1);
        if(entrySet[hashIndex] == null) {
            Entry<K, V> entry = new Entry<>();
            entry.key = key;
            entry.value = value;
            entry.hashcode = value.hashCode();
            entrySet[hashIndex] = entry;
            return null;
        }

        Entry<K, V> entry = entrySet[hashIndex];
        while (true) {
            if (entry.hashcode == value.hashCode()) {
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
        newEntry.hashcode = value.hashCode();
        entry.next = newEntry;
        return null;
    }
}
