package com.future.experience.linying;

import java.util.Stack;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String token : tokens) {
            if(token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if(token.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if(token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if(token.equals("/")) {
                int val1 = stack.pop();
                stack.push(stack.pop() / val1);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
