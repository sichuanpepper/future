package com.future.experience.box.memorydatabase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xingfeiy on 8/19/18.
 */
public class KVSession<K, V> implements KVDBInterface<K, V> {

    public KVSession(long sessionId, ConcurrentHashMap<K, V> data, ConcurrentHashMap<V, Long> valueCounter) {
        this.sessionId = sessionId;
        this.data = data;
        this.valueCounter = valueCounter;
    }

    private long sessionId = 0l;

    private ConcurrentHashMap<K, V> data = null;

    private Map<K, V> localData = new HashMap<>();

    private ConcurrentHashMap<V, Long> localValueCounter = new ConcurrentHashMap<>();

    private ConcurrentHashMap<V, Long> valueCounter = null;

    private Set<K> deletedKeys = new HashSet<>();

    private boolean transStarted = false;

    @Override
    public V set(K key, V val) {
        //if local data is not null, means current session started a transaction.
        if(transStarted) {
            return localData.put(key, val);
        }
        return data.put(key, val);
    }

    @Override
    public V get(K key) {
        if(transStarted && localData.containsKey(key)) {
            return localData.get(key);
        }
        return data.get(key);
    }

    @Override
    public long count(V val) {
        return localValueCounter.getOrDefault(val, 0l) + valueCounter.getOrDefault(val, 0l);
    }

    @Override
    public V delete(K key) {
        if(localData.containsKey(key)) localData.remove(key);
        deletedKeys.add(key);
        return null;
    }

    @Override
    public boolean beginTransaction() {
        transStarted = true;
        return true;
    }

    @Override
    public boolean commit() {
        synchronized (data) {
            data.putAll(localData);
            valueCounter.putAll(localValueCounter);
            for(K key : deletedKeys) data.remove(key);
        }
        return true;
    }

    @Override
    public boolean rollback() {
        clearCurrentLocalData();
        return true;
    }

    private void clearCurrentLocalData() {
        localValueCounter.clear();
        localData.clear();
        deletedKeys.clear();
        transStarted = false;
    }

    public boolean close() {
        clearCurrentLocalData();
        return true;
    }
}
