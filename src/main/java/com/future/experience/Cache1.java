package com.future.experience;

import java.util.*;

/**
 * 给两个API 存一个数字和取一个数字
 *
 * 求每次retrieve 取出里面出现次数最多number，并且把那个数字的出现次数-1. 有个条件，如果出现次数一样，后插入的先取出来。没有数字就报错
 */
public class Cache1 {

    private Map<Integer, Integer> counter = new HashMap<>();

    private Map<Integer, LinkedHashSet<Integer>> map = new HashMap<>();

    private int max = 0;

    public void store(int val) {
        if(!counter.containsKey(val)) {
            counter.put(val, 1);
            LinkedHashSet<Integer> set = map.getOrDefault(1, new LinkedHashSet<>());
            set.add(val);
            map.put(1, set);
            max = Math.max(max, 1);
        } else {
            int cnt = counter.get(val);
            counter.put(val, cnt + 1);

            LinkedHashSet<Integer> set = map.get(cnt);
            set.remove(val);
            if(set.isEmpty()) {
                map.remove(cnt);
            } else {
                map.put(cnt, set);
            }

            set = map.getOrDefault(cnt + 1, new LinkedHashSet<>());
            set.add(val);
            max = Math.max(max, cnt + 1);

        }
    }

    private void addFirst(LinkedHashSet<Integer> set, int val) {

    }

    //how to
    public int retrieve() {
        //
        return 0;
    }
}
