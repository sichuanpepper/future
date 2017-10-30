package com.future.foundation.java;

import com.future.utils.DisplayUtils;

import java.util.Stack;

/**
 * Created by someone on 9/21/17.
 */
public class Misc {
    public static void primitiveType() {
        //An object is a class instance or an array. So you don't have to init as Stack<int[]>
        Stack<int[]> stack = new Stack<>();

        int[][] array = new int[1][1];
        array[0] = new int[]{0, 1};
        for(int[] tmp : array) {
            stack.push(tmp);
        }
        while (!stack.isEmpty()) {
            DisplayUtils.printArray(stack.pop());
        }

        for(int[] tmp : array) {
            stack.push(tmp);
        }
        //yes, stack also provide function clear();
        stack.clear();
        while (!stack.isEmpty()) {
            DisplayUtils.printArray(stack.pop());
        }

        stack.push(new int[]{1, 2});
        int pos = stack.indexOf(new int[]{1, 2});
        if(pos < 0) {
            System.out.println("Can't find it.");
        }
    }

    public static void main(String[] args) {
        primitiveType();
    }
}
