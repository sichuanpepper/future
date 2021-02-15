package com.future.experience.gugou;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/hand-of-straights/
 */
public class HandofStraights {
    /**
     hand: all positive, duplicated, unsorted
     hand.length % w == 0


     **/
    public boolean isNStraightHand(int[] hand, int W) {
        if(hand.length % W != 0) return false;
        //key: number, Value: count
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }

        while(!map.isEmpty()) {
            Map.Entry<Integer, Integer> entry = map.firstEntry();
            int start = entry.getKey();
            for(int i = 0; i < W; i++) {
                if(!map.containsKey(start)) {
                    return false;
                }
                int val = map.get(start);
                if(val == 1) {
                    map.remove(start);
                } else {
                    map.put(start, val - 1);
                }
                start++;
            }
        }
        return true;
    }
}
