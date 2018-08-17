package com.future.experience.box.memorydatabase;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xingfeiy on 8/17/18.
 */
public class KVSegment<K, V> {
    private int segmentId;

    private ConcurrentHashMap<K, KVDBValue<V>> map = new ConcurrentHashMap<>();

}
