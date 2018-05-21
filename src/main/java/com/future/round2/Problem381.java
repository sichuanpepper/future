package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/description/
 * Follow up question about 310
 * Note: Duplicate elements are allowed.
 *
 * Created by xingfeiy on 5/20/18.
 */
public class Problem381 {
    //key -> element, value -> the position in array
    private Map<Integer, Set<Integer>> map = new HashMap<>();

    private List<Integer> list = new ArrayList<>();

    /** Initialize your data structure here. */
    public Problem381() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean res = !map.containsKey(val);
        Set<Integer> pos = map.getOrDefault(val, new HashSet<>());
        pos.add(list.size());
        map.put(val, pos);
        list.add(val);
        return res;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        Set<Integer> pos = map.get(val);
        if(pos == null || pos.size() < 1) return false;

        int index = pos.iterator().next();
        pos.remove(index);

        if(index != list.size() - 1) {
            int tail = list.get(list.size() - 1);
            list.set(index, tail);
            map.get(tail).remove(list.size() - 1);
            map.get(tail).add(index);
        }
        list.remove(list.size() - 1);
        if(map.get(val).isEmpty()) map.remove(val);
        return true;
    }

    private Random random = new Random();
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
