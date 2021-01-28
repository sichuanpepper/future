package com.future.experience.gugou;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-ii/
 */
public class MyCalendarII {
    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        //check overlaps
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
            if(count > 2) {
                //can't book and rollback the putted data
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                if(map.get(start) == 0) {
                    map.remove(start);
                }
                if(map.get(end) == 0) {
                    map.remove(end);
                }
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        MyCalendarII p = new MyCalendarII();
        System.out.println(p.book(10, 20));
        System.out.println(p.book(10, 20));
        System.out.println(p.book(10, 20));
        System.out.println(p.book(10, 20));
        System.out.println(p.book(10, 20));
    }
}
