package com.future.experience.linying;

import java.util.*;

/**
 * 第二题类似LRU Cache但是Eviction条件不同，Put的时候自带权重，要求Evict权重最低
 * 的。HashMap+PriorityQueue搞定。然后分析Multi-thread的情况，分析哪里可能是瓶颈，
 * 提出可以用ConcurrentHashMap + MyLock on Queue。
 */
public class CacheWithWeight {
    private class WeightedKey {
        private int key;

        private float weight;

        public WeightedKey(int key, float weight) {
            this.key = key;
            this.weight = weight;
        }

//        @Override
//        public boolean equals(Object obj) {
//            if(this == obj) {
//                return true;
//            }
//            if (obj == null || getClass() != obj.getClass()) return false;
//            WeightedKey wk = (WeightedKey)obj;
//            return this.key == wk.key;
//        }
//
//        @Override
//        public int hashCode() {
//            return this.key;
//        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WeightedKey that = (WeightedKey) o;
            return key == that.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }


    /**
     * Takeaways:
     * TreeSet and TreeMap doesn't use hashcode at all, it use compareTo or Comparator you pass to the construction.
     */
    private TreeMap<WeightedKey, Integer> map = new TreeMap<>((a, b) -> {
        if(a.key == b.key) return 0;
        return Float.compare(a.weight, b.weight);
    });

    private int capacity;

    public CacheWithWeight(int capacity) {
        this.capacity = capacity;
    }

    //Ask what's the strategy if the key is presented in the cache.
    //Here let's assume if key is presented, we will overwrite the old value and weight.
    public void put(int key, int val, float weight) {
        WeightedKey wk = new WeightedKey(key, weight);
        map.remove(wk);
        if(map.size() == this.capacity) {
            evict();
        }
        map.put(wk, val);
    }

    private void evict() {
        this.map.remove(map.firstKey());
    }

    public int get(int key) {
//        for(WeightedKey wk : this.map.keySet()) {
//            System.out.println(wk.hashCode());
//        }
        WeightedKey wk = new WeightedKey(key, 0.0f);
//        WeightedKey wk1 = new WeightedKey(key, 0.3f);
//        if(wk.equals(wk1)) {
//            System.out.println("equals to");
//        }
//        Set<WeightedKey> set = new TreeSet<>((a, b)->Float.compare(a.weight, b.weight));
//        set.add(wk);
//        set.add(wk1);
//        System.out.println(set.size());
//        if(this.map.keySet().contains(wk)) {
//            System.out.println("Contain key!");
//        }
//        System.out.println(wk.hashCode());
        if(this.map.containsKey(wk)) {
            return this.map.get(wk);
        }
        return -1;
    }

    public static void main(String[] args) {
        CacheWithWeight cache = new CacheWithWeight(5);
        cache.put(2, 20, 0.2f);
        cache.put(4, 40, 0.4f);
        cache.put(1, 10, 0.1f);
        cache.put(5, 50, 0.5f);
        cache.put(3, 30, 0.3f);
        System.out.println(cache.get(1)); //10
        cache.put(6, 60, 0.6f);
        System.out.println(cache.get(1));  //-1
        System.out.println(cache.get(2));  //20
        cache.put(2, 200, 0.6f);
        System.out.println(cache.get(2)); //20
        cache.put(7, 70, 0.7f);
        System.out.println(cache.get(3));  //-1
    }
}
