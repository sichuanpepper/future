package com.future.experience.linying;

import com.future.experience.linying.MaxStack;

import java.util.*;

/**
 * Double linked list + tree map solution
 * push: add to list, and map, O(log(n))
 * pop: O(1)
 * top: O(1)
 * peekMax: O(1)
 * popMax: O(1)
 *
 * Created by xingfeiy on 6/17/18.
 */
public class MaxStack2 {
    private List<Integer> list = new LinkedList<>();

    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public void push(int val) {
        list.add(val);
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    public int pop() {
        if(list.isEmpty()) return Integer.MIN_VALUE;
        int val = list.remove(list.size() - 1);
        if(map.get(val) == 1) map.remove(val);
        map.put(val, map.get(val) - 1);
        return val;
    }

    public int top() {
        if(list.isEmpty()) return Integer.MIN_VALUE;
        return list.get(list.size() - 1);
    }

    public int peekMax() {
        if(map.isEmpty()) return Integer.MIN_VALUE;
        return map.firstEntry().getKey();
    }

    public int popMax() {
        int max = map.firstKey();
        list.remove(max);
        return max;
    }

    public static void main(String[] args) {
        MaxStack ms = new MaxStack();
        System.out.println(ms.top());  //min value
        ms.push(3);
        ms.push(5);
        ms.push(2);

        System.out.println(ms.top());  //2
        System.out.println(ms.peekMax());  //5
        System.out.println(ms.pop()); //2
        System.out.println(ms.peekMax());  //5
        System.out.println(ms.pop()); //5
        System.out.println(ms.peekMax());  //3
        ms.push(6);
        ms.push(6);
        System.out.println(ms.peekMax());  //6
        System.out.println(ms.popMax());  //6
        System.out.println(ms.popMax());  //6
        System.out.println(ms.popMax());  //3
        System.out.println(ms.popMax());  //min value
    }
}
