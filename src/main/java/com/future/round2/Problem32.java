package com.future.round2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 For "(()", the longest valid parentheses substring is "()", which has length = 2.

 Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

 * Created by someone on 10/29/17.
 */
public class Problem32 {
    /**
     * Analyze:
     * 1. To perform valid parentheses substing, the first data structure in mind is Stack.
     * The problem actually is find the contiguous pairs pop out from stack.
     *
     * We can simply push characters into stack one by one, except we encountered a pair.
     * When we encountered a pair, just pop it out and calculate the length.
     * The stack could be empty, which means all characters are produced well-formed until current one.
     *
     * Example, "((())"
     *
     * @return
     */
    public static int longestValidParentheses(String s) {
        if(s == null || s.length() < 1) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                //
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    //encountered a pair
                    stack.pop();
                    //the last means where the last contiguous subarray(could be either well-formed or bad-formed) ended.
                    int last = stack.isEmpty() ? -1 : stack.peek();
                    max = Math.max(max, i - last);
                } else {
                    //the current character can't produce a well-formed subarray, push it into the peek of stack.
                    stack.push(i);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(""));
        System.out.println(longestValidParentheses(")"));
        System.out.println(longestValidParentheses("("));
        System.out.println(longestValidParentheses("())()"));
        System.out.println(longestValidParentheses("()(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("))))))"));
        System.out.println(longestValidParentheses("(((((("));
        System.out.println(longestValidParentheses("()()((()))"));
        System.out.println(longestValidParentheses(")(((((((())"));
    }
}
