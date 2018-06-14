package com.future.round2;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/hand-of-straights/description/
 *
 * Created by xingfeiy on 6/5/18.
 */
public class Problem846 {
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i : hand) map.put(i, map.getOrDefault(i, 0) + 1);

        while(!map.isEmpty()) {
            Map.Entry<Integer, Integer> firstEntry = map.firstEntry();
            for(int i = 0; i < W; i++) {
                //if there's no consecutive value, return false;
                if(map.get(firstEntry.getKey() + i) < 1) return false;
                //update map, if the count of current key is 0, remove it, otherwise update count.
                int tmp = map.get(firstEntry.getKey() + i) - 1;
                map.put(firstEntry.getKey() + i, tmp);
            }
        }
        return true;
    }
}
