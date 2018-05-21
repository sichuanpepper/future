package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/description/
 *

 Design a data structure that supports all following operations in average O(1) time.

 insert(val): Inserts an item val to the set if not already present.
 remove(val): Removes an item val from the set if present.
 getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 Example:

 // Init an empty set.
 RandomizedSet randomSet = new RandomizedSet();

 // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomSet.insert(1);

 // Returns false as 2 does not exist in the set.
 randomSet.remove(2);

 // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomSet.insert(2);

 // getRandom should return either 1 or 2 randomly.
 randomSet.getRandom();

 // Removes 1 from the set, returns true. Set now contains [2].
 randomSet.remove(1);

 // 2 was already in the set, so return false.
 randomSet.insert(2);

 // Since 2 is the only number in the set, getRandom always return 2.
 randomSet.getRandom();

 * Analyze:
 * Since the problem required to support insert, remove and random in O(1) time, and actually it also includes lookup.
 * think about the data structures we have.
 *
 * Set, insert && remove && lookup are O(1), but get?
 *
 * ArrayList, insert could be O(1), just insert it at tail, get also O(1),but remove && lookup?
 *
 * So, it seems that we have to use combination since one collection can't meet all requirements.
 *
 * The solution is HashMap + ArrayList
 *
 * HashMap: key is the insert element, and value is the position of this element in array. Insert O(1)
 * ArrayList: Find a element can be in O(1) time, but how about remove? that's not O(1) except the tail, so once we found element,
 * we can simply swap it with tail, and remove tail.
 *
 * Created by xingfeiy on 5/20/18.
 */
public class Problem380 {
    //key -> element, value -> the position in array
    private Map<Integer, Integer> map = new HashMap<>();

    private List<Integer> list = new ArrayList<>();

    /** Initialize your data structure here. */
    public Problem380() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int index = map.remove(val);
        if(index != list.size() - 1) {
            int tail = list.get(list.size() - 1);
            list.set(index, tail);
            map.put(tail, index);
        }
        list.remove(list.size() - 1);
        return true;
    }

    private Random random = new Random();
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
