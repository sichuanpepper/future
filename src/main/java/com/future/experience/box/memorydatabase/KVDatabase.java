package com.future.experience.box.memorydatabase;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Assumption:
 *  - database scale: 1B
 *  - key/value types: simple type
 *      - If key/value are long type, 16GB is required
 *      - Counter, another 16GB is required
 *  - implicit transaction ~ 90%
 *  - explicit transaction ~ 10%
 *      - avg atomic(single transaction) operations ~ 10
 *  - get ~ 70%
 *  - set ~ 20%
 *  - delete ~ 10%
 *
 *  - bottleneck
 *      - Read from memory is 40 times faster than Ethernet
 *
 * Design:
 *  - The whole database consists of multiple segments(default by 16).
 *      - Scale to bigger database size, hashmap's size is limited.
 *      - Performance.
 *
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
public class KVDatabase<K, V> {
    private long capacity = 100000000;

    private int numOfSeg = 10;

    private ConcurrentHashMap<V, Long> valueCounter = new ConcurrentHashMap<>();

    private ConcurrentHashMap<K, V> data = new ConcurrentHashMap<>();

    public KVDatabase(long capacity, int numOfSeg) {
        this.capacity = capacity;
        this.numOfSeg = numOfSeg;
    }

    public KVSession getSession() {
        return new KVSession(getSessionId(), data, valueCounter);
    }

    protected long getSessionId() {
        return 0l;
    }
}
