package com.future.foundation.java;

import com.future.utils.DisplayUtils;

import java.util.*;

/**
 * Created by someone on 7/8/17.
 * We are going to talk about one of the commonest collections Map here, we maybe already familiar with HashMap, maybe not.
 *
 * There are other useful function excepts put, get, containsKey...
 *
 * And we also will introduce the TreeMap and LinkedHashMap here, we don't use it often in practice.
 *
 * The last, we will talk a little bit about the thread safe maps.
 */
public class MapLearning {
    public static void mapIntro() {
        Map<Integer, Integer> map = new HashMap<>();
        //both the key and value are allowed to be null in HashMap.
        map.put(null, null);
        System.out.println(map.get(null));  //output null

        //the method put also has a return value.
        //if null is returned, it means:
        //the key isn't existed in the map, or the key associated with a null value.
        map.put(1, 2);
        int ret = map.put(1, 3);
        System.out.println("The result returned by put is " + ret);  //out 2

        //this is simple
        int val = map.getOrDefault(3, 3);
    }

    /**
     * The introduce of interface SortedMap
     *
     * Known subinterfaces:
     * ConcurrentNevigableMap, NevigableMap
     *
     * Known implementation
     * TreeMap, ConcurrentSkipListMap
     *
     */
    public static void sortedMapIntro() {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        map.put(4, 2);
        map.put(2, 2);
        map.put(1, 2);

        //output:
        //1 => 2
        //2 => 2
        //4 => 2
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        System.out.println("The first key is: " + map.firstKey()); // return 1
        System.out.println("The last key is: " + map.lastKey()); // return 4

        //Returns a view of the portion of this map whose keys are strictly less than toKey.
        //output:
        //1 => 2
        SortedMap<Integer, Integer> lessThan2Map = map.headMap(2);
        for(Map.Entry<Integer, Integer> entry : lessThan2Map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        //Returns a view of the portion of this map whose keys are greater than or equal to fromKey.
        //output:
        //2 => 2
        //4 => 2
        SortedMap<Integer, Integer> largerThan2Map = map.tailMap(2);
        for(Map.Entry<Integer, Integer> entry : largerThan2Map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        //Returns a view of the portion of this map whose keys range from fromKey, inclusive, to toKey, exclusive.
        //output:
        //4 => 2
        SortedMap<Integer, Integer> subMap = map.subMap(2, 5);
        for(Map.Entry<Integer, Integer> entry : subMap.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        //remember that, it's a view of original map.
        lessThan2Map.put(1, 0);
        System.out.println("See if the value of 1 in original map got changed or not: " + map.get(1)); // return 0. changed.
    }

    public static void navigableMap() {
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        map.put(3, 4);
        map.put(6, 9);
        map.put(1, 7);

        //Returns a key-value mapping associated with the least key greater than or equal to the given key,
        // or null if there is no such key.
        Map.Entry<Integer, Integer> entry = map.ceilingEntry(6);
        System.out.println(entry.getKey() + " => " + entry.getValue()); //6 => 9
        entry = map.ceilingEntry(2);
        System.out.println(entry.getKey() + " => " + entry.getValue()); //3 => 4

        //Returns a key-value mapping associated with the greatest key less than or equal to the given key,
        // or null if there is no such key.
        entry = map.floorEntry(2);
        System.out.println(entry.getKey() + " => " + entry.getValue()); //1 => 7

        entry = map.floorEntry(12);
        System.out.println(entry.getKey() + " => " + entry.getValue()); //6 => 9

        //Returns the least key greater than or equal to the given key, or null if there is no such key.
        System.out.println(map.ceilingKey(2));  // 3

        NavigableSet<Integer> set = map.descendingKeySet();
        DisplayUtils.printCollection(set);  //6 3 1

        entry = map.firstEntry();
        System.out.println(entry.getKey() + " => " + entry.getValue()); //1 => 7

        //Returns a reverse order view of the mappings contained in this map.
        entry = map.descendingMap().firstEntry();
        System.out.println(entry.getKey() + " => " + entry.getValue());  //6 => 9

        entry = map.firstEntry();
        System.out.println(entry.getKey() + " => " + entry.getValue()); //1 => 7



    }

    public static void main(String[] args) {
        mapIntro();
        sortedMapIntro();
        navigableMap();
    }
}
