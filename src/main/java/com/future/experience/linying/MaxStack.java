package com.future.experience.linying;

import java.util.Stack;

/**
 * Two stacks solution
 * push: O(1)
 * pop: O(1)
 * top: O(1)
 * peekMax: O(1)
 * popMax: O(n)
 *
 * Space complexity O(n)
 * Created by xingfeiy on 6/17/18.
 */
public class MaxStack {
    private Stack<Integer> stack = new Stack<>();

    private Stack<Integer> maxStack = new Stack<>();

    public void push(int val) {
        stack.push(val);
        if(maxStack.isEmpty() || val >= maxStack.peek()) {
            maxStack.push(val);
        }
    }

    public int pop() {
        if(stack.isEmpty()) return Integer.MIN_VALUE;
        int val = stack.pop();
        if(val == maxStack.peek()) maxStack.pop();
        return val;
    }

    public int top() {
        if(stack.isEmpty()) return Integer.MIN_VALUE;
        return stack.peek();
    }

    public int peekMax() {
        if(maxStack.isEmpty()) return Integer.MIN_VALUE;
        return maxStack.peek();
    }

    public int popMax() {
        if(maxStack.isEmpty()) return Integer.MIN_VALUE;
        int max = maxStack.pop();
        stack.remove(new Integer(max));  //there are two remove methods, primitive int is removing element by index.
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
