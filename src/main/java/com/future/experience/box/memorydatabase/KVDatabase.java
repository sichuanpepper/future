package com.future.experience.box.memorydatabase;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xingfeiy on 8/17/18.
 */
public class KVDatabase<K, V> implements KVDBInterface<K, V> {
    private long capacity = 100000000;

    private int numOfSeg = 10;

    private ConcurrentHashMap<Integer, KVSegment> segments = new ConcurrentHashMap<>();

    private ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

    public KVDatabase(long capacity, int numOfSeg) {
        this.capacity = capacity;
        this.numOfSeg = numOfSeg;
        for(int i = 0; i < numOfSeg; i++) {
            segments.put(i, new KVSegment<K, V>());
        }
    }

    @Override
    public V set(K key, V val) {
        return map.put(key, val);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public int count(V val) {
        return 0;
    }

    @Override
    public V delete(K key) {
        return map.remove(key);
    }

    @Override
    public boolean beginTransaction() {
        return false;
    }

    @Override
    public boolean commit() throws ConflictException {
        return false;
    }

    @Override
    public boolean rollback() {
        return false;
    }

    protected long hash(K key) {
        //todo
        return 0l;
    }

    private KVSegment getSegment(K key) {
        int mod = (int)(hash(key) % segments.size());
        return segments.get(mod);
    }
}
