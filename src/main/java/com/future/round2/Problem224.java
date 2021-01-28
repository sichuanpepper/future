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

    public int calculate2(String s) {
        int preSign = 1; // 1 means +, and -1 means -
        int res = 0, index = 0, reverse = 1;
        while (index < s.length()) {
            if(Character.isDigit(s.charAt(index))) {
                int num = 0;
                while(index < s.length() && Character.isDigit(s.charAt(index))) {
                    num = num * 10 + (s.charAt(index++) - '0');
                }
                res = res + preSign * reverse * num;
            } else if(s.charAt(index) == '(') {
                reverse = preSign;
                if(Character.isDigit(s.charAt(index + 1))) {
                    preSign = 1;
                }
                index++;
            } else if(s.charAt(index) == ')') {
                index++;
            } else if(s.charAt(index) == '+') {
                preSign = 1;
                index++;
            } else if(s.charAt(index) == '-') {
                preSign = -1;
                index++;
            } else {
                index++;
            }
        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println(new Problem224().calculate2("1-(5)"));
    }
}
