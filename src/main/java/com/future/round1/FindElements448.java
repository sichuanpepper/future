package com.future.round1;

import java.util.LinkedList;
import java.util.List;

/**
 * //Two important key points
 * 1. The construction function doesn't take a integer as initial length, why?
 *
 * 2. The parameter of remove function which is index of element rather than the value of element,
 * how about remove function of ArrayList?
 *
 * Q1, it's a linked list, buddy, we don't need allocate a consecutive memory for linked list.
 * Q2, both linked list and array list have two remove function, one for index and other one for object.
 * First one take primitive int type as parameter, and second one take object, so you must be understood how to use it now.
 *
 * Created by someone on 6/17/17.
 */
public class FindElements448 {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new LinkedList();
        for(int i = 0; i < nums.length; i++) {
            list.add(i + 1);
        }

        for(Integer num : nums) {
            list.remove(num);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers(array));
    }
}
