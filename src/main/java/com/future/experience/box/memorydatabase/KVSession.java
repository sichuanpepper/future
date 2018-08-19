package com.future.experience.box.memorydatabase;

/**
 * Created by xingfeiy on 8/19/18.
 */
public class KVSession<K, V> implements KVDBInterface<K, V> {
    private long sessionId = 0l;

    public KVSession(long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public V set(K key, V val) {
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int count(V val) {
        return 0;
    }

    @Override
    public V delete(K key) {
        return null;
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

    public boolean close() {
        return true;
    }
}
