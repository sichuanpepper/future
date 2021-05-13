package com.future.experience.linying;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 实现RetainBestCache class, 基于rank来remove data from cache, 给了Rank类和data_sourc类。初始化输入为data_source和内存size，主要是实现get(Key)，返回key对应的内容
 */
public class RetainBestCache <K, T extends Rankable> {

    private int entriesToRetain;

    // key value store
    private HashMap<K, T> cache = new HashMap<K,T>();

    private TreeMap<Long, LinkedList<K>> rankMap = new TreeMap<>();

    private DataSource<K,T> ds;

    private int capacity;

    /* Constructor with a data source (assumed to be slow) and a cache size */
    public RetainBestCache(DataSource<K,T> ds, int entriesToRetain) {
        //impliment here
        this.ds = ds;
        this.capacity = entriesToRetain;
    }

    /* Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
     * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
     * evicting the T with lowest rank among the ones that it has available
     * If there is a tie, the cache may choose any T with lowest rank to evict.
     */
    public T get(K key) {
        if(cache.containsKey(key)) {
            return cache.get(key);
        }

        //retrieve it from data source
        T t = this.ds.get(key);
        if(t == null) {
            return null; //talking about the strategy here. Cache Penetration
        }

        if(cache.size() == capacity) {
            evict();
        }

        cache.put(key, t);
        Long rank = t.getRank();
        LinkedList list = rankMap.getOrDefault(key, new LinkedList<>());
        list.add(key);
        rankMap.put(rank, list);
        return t;
    }

    private void evict() {
        Map.Entry<Long, LinkedList<K>> firstEntry = rankMap.firstEntry();
        cache.remove(firstEntry.getValue().getLast());
        firstEntry.getValue().removeLast();
        if(firstEntry.getValue().size() == 0) {
            rankMap.remove(firstEntry.getKey());
        }
    }

    /*
     * For reference, here are the Rankable and DataSource interfaces.
     * You do not need to implement them, and should not make assumptions
     * about their implementations.
     */

/**
    public interface Rankable {
        long getRank();
    }

    public interface DataSource<K, T extends Rankable> {
        T get(K key);
    }
 **/
}
