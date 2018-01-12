package com.future.round2;

import java.util.Stack;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
 *
 Evaluate the value of an arithmetic expression in Reverse Polish Notation.

 Valid operators are +, -, *, /. Each operand may be an integer or another expression.

 Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

 * Created by xingfeiy on 1/10/18.
 */
public class Problem150 {
    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length < 1) return Integer.MIN_VALUE;
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < tokens.length; i++) {
            if(!tokens[i].equals("+") && !tokens[i].equals("-")  && !tokens[i].equals( "*") && !tokens[i].equals("/")) {
                stack.push(tokens[i]);
            } else {
                long val1 = Long.parseLong(stack.pop());
                long val2 = Long.parseLong(stack.pop());
                if(tokens[i].equals("+")) {
                    stack.push(Long.toString(val1 + val2));
                } else if(tokens[i].equals("-")) {
                    stack.push(Long.toString(val2 - val1));
                } else if(tokens[i].equals("/")) {
                    stack.push(Long.toString(val2 / val1));
                } else if(tokens[i].equals("*")) {
                    stack.push(Long.toString(val1 * val2));
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
