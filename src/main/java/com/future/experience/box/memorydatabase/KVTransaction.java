package com.future.experience.box.memorydatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingfeiy on 8/17/18.
 */
public class KVTransaction<K, V> {
    private long transId;

    private Map<K, V> localData = new HashMap<>();

    public KVTransaction(long transId, Map<K, V> localData) {
        this.transId = transId;
        this.localData = localData;
    }

    public long getTransId() {
        return transId;
    }

    public void setTransId(long transId) {
        this.transId = transId;
    }

    public Map<K, V> getLocalData() {
        return localData;
    }

    public void setLocalData(Map<K, V> localData) {
        this.localData = localData;
    }
}
