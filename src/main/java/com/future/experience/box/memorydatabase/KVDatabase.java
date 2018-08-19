package com.future.experience.box.memorydatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * - set, get and delete call commit implicitly
 * - begin transaction
 *      - set (all changes are available in current session, place lock if needed)
 *      - get
 *      ...
 * - commit(or rollback)
 *      - after commit, the change will be available for other sessions
 *          - Placing locks when we commit the changes.
 *      - or after rollback, there's nothing changed.
 *
 * - Once a session starts a transaction
 *      - all operations will be implemented in current session util commit
 *      - for the set or delete operations, the lock is required.
 *
 * - Define a database.
 * - A factory pattern to create a new session.
 * - An interface defined the operation, and session implements this interface.
 *
 * Created by xingfeiy on 8/17/18.
 */
public class KVDatabase<K, V> implements KVDBInterface<K, V> {
    private long capacity = 100000000;

    private int numOfSeg = 10;

    private ConcurrentHashMap<V, Long> valueCounter = new ConcurrentHashMap<>();

    private ConcurrentHashMap<Integer, KVSegment> segments = new ConcurrentHashMap<>();

    private ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

    // key: session id, value: the opening transaction
    private Map<Long, KVTransaction<K, V>> sessionTranMap = new HashMap<>();

    public KVDatabase(long capacity, int numOfSeg) {
        this.capacity = capacity;
        this.numOfSeg = numOfSeg;
        for(int i = 0; i < numOfSeg; i++) {
            segments.put(i, new KVSegment<K, V>());
        }
    }

    /**
     * - Check if there's an opening transaction
     *  - No, add it to database directly.
     *  - Yes, add it to the current transaction
     * @param key
     * @param val
     * @return
     */
    @Override
    public V set(K key, V val) {
        KVTransaction transaction = sessionTranMap.get(getSessionId());
        if(transaction == null) return map.put(key, val);
        return (V)transaction.getLocalData().put(key, val);
    }

    @Override
    public V get(K key) {
        KVTransaction transaction = sessionTranMap.get(getSessionId());
        if(transaction == null) return map.get(key);
        return (V)transaction.getLocalData().get(key);
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

    protected long getSessionId() {
        return 0l;
    }
}
