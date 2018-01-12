package com.future.round2;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-ii/description/
 *
 Implement a basic calculator to evaluate a simple expression string.

 The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

 You may assume that the given expression is always valid.

 Some examples:
 "3+2*2" = 7
 " 3/2 " = 1
 " 3+5 / 2 " = 5
 Note: Do not use the eval built-in library function.

 * Created by xingfeiy on 1/4/18.
 */
public class Problem227 {
    /**
     * Analyze:
     * Use stack, traversal the charcter one by one
     *  - if char is digits, push it to stack
     *  - if char is '+' or '-', push it to stack.
     *  - if char is '*' or '/', pop one out and do the math.
     *  - if char is ' ', do nothing
     * @param s
     * @return
     */
    public int calculate(String s) {
        return 0;
    }
}
