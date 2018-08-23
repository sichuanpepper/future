package com.future.experience.box.memorydatabase;

/**
 * Created by xingfeiy on 8/17/18.
 */
public interface KVDBInterface<K, V> {
    V set(K key, V val);

    V get(K key);

    long count(V val);

    V delete(K key);

    boolean beginTransaction();

    boolean commit();

    boolean rollback();


}
