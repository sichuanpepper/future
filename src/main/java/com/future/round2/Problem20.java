package com.future.round2;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * Created by someone on 10/31/17.
 */
public class Problem20 {
    public static boolean isValid(String s) {
        if(s == null || s.length() < 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                if(stack.isEmpty()) return false;
                char ch = stack.pop();
                if(s.charAt(i) == ')' && ch != '(') return false;
                if(s.charAt(i) == ']' && ch != '[') return false;
                if(s.charAt(i) == '}' && ch != '{') return false;
            }
        }
        return stack.isEmpty();
    }
}
