package com.future.experience.box.memorydatabase;


/**
 * Created by xingfeiy on 8/17/18.
 */
public class KVDBValue<V> {
    private V value;

    private long tstamp;

    public KVDBValue(V value) {
        this.value = value;
        this.tstamp = System.currentTimeMillis();
    }
}
