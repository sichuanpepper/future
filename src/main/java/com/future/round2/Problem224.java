package com.future.round2;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/description/
 *
 * Created by xingfeiy on 6/12/18.
 */
public class Problem224 {
    public int calculate(String s) {
        if(s == null || s.length() < 1) return 0;
        Stack<Integer> stack = new Stack<>();
        int curVal = 0, sign = 1, curPos = 0;
        while(curPos < s.length()) {
            if(Character.isDigit(s.charAt(curPos))) {
                int end = curPos + 1;
                while(end < s.length() && Character.isDigit(s.charAt(end))) end++;
                curVal += sign * Integer.parseInt(s.substring(curPos, end));
                curPos = end;
                continue;
            } else if(s.charAt(curPos) == '(') {
                stack.push(curVal);
                stack.push(sign);
                curVal = 0;
                sign = 1;
            } else if(s.charAt(curPos) == '+') {
                sign = 1;
            } else if(s.charAt(curPos) == '-') {
                sign = -1;
            } else if(s.charAt(curPos) == ')') {
                if(!stack.isEmpty()) curVal = stack.pop() * curVal + stack.pop();
            }
            curPos++;
        }


        while(!stack.isEmpty()) {
            curVal += stack.pop() * stack.pop();
        }
        return curVal;
    }
}
