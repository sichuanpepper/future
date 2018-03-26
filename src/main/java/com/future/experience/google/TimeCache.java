package com.future.experience.google;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by xingfeiy on 3/22/18.
 */
public class TimeCache {
    private class CacheValue {
        private Timestamp timestamp;

        private String value;

        public CacheValue(Timestamp timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    private TreeMap<String, CacheValue> map;

    public TimeCache() {
        this.map = new TreeMap(new Comparator<Map.Entry<String, CacheValue>>() {
            @Override
            public int compare(Map.Entry<String, CacheValue> o1, Map.Entry<String, CacheValue> o2) {
                return o1.getValue().timestamp.compareTo(o2.getValue().timestamp);
            }
        });
    }

    public void set(Timestamp timestamp, String key, String value) {
        this.map.put(key, new CacheValue(timestamp, value));
    }

    public String get(String key) {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        while (this.map.firstEntry().getValue().timestamp.before(current)) {
            this.map.pollFirstEntry();
        }
        return this.map.containsKey(key) ? this.map.get(key).value : "";
    }
}
